package uk.stevebosman.aoc2023.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PipeMap {
  final List<String> lines;
  final Coordinate start;
  final List<Coordinate> firstSteps;

  public PipeMap(final String input) {
    this.lines = new ArrayList<>(input.lines()
                      .toList());
    this.start = getStartX();
    this.firstSteps = getFirstSteps();
    fixStart();
    if (this.firstSteps.size() != 2) throw new IllegalArgumentException("Unusual first steps: " + firstSteps);
  }

  private Coordinate getStartX() {
    for (int row = 0; row < lines.size(); row++) {
      final int column = lines.get(row)
                              .indexOf('S');
      if (column != -1) {
        return new Coordinate(row, column);
      }
    }
    throw new IllegalArgumentException("no starting position");
  }

  public List<Coordinate> getFirstSteps() {
    final List<Coordinate> result = new ArrayList<>();
    if (isConnected(start.north(), Set.of('|', '7', 'F'))) {
      result.add(start.north());
    }
    if (isConnected(start.south(), Set.of('|', 'L', 'J'))) {
      result.add(start.south());
    }
    if (isConnected(start.east(), Set.of('-', '7', 'J'))) {
      result.add(start.east());
    }
    if (isConnected(start.west(), Set.of('-', 'L', 'F'))) {
      result.add(start.west());
    }

    return result;
  }

  public void fixStart() {
    final boolean north = isConnected(start.north(), Set.of('|', '7', 'F'));
    final boolean south = isConnected(start.south(), Set.of('|', 'L', 'J'));
    final boolean east = isConnected(start.east(), Set.of('-', '7', 'J'));
    final boolean west = isConnected(start.west(), Set.of('-', 'L', 'F'));

    Character startChar = null;
    if (north && south) startChar = '|';
    else if (north && east) startChar = 'L';
    else if (north && west) startChar = 'J';
    else if (south && east) startChar = 'F';
    else if (south && west) startChar = '7';
    else if (east && west) startChar = '-';

    if (startChar != null) {
      final String fixedLine = lines.get(start.row()).replace('S', startChar);
      lines.set(start.row(), fixedLine);
    }
  }

  private boolean isConnected(final Coordinate position, final Set<Character> connectable) {
    final Optional<Character> charAtPosition = get(position);
    return charAtPosition.filter(connectable::contains)
                         .isPresent();
  }

  public Optional<Character> get(final Coordinate coordinate) {
    if (coordinate.row() < 0 || coordinate.row() >= lines.size()
            || coordinate.column() < 0 || coordinate.column() >= lines.get(0).length()) {
      return Optional.empty();
    }
    return Optional.of(lines.get(coordinate.row())
                .charAt(coordinate.column()));
  }

  private Coordinate nextPosition(final Coordinate position, final Coordinate banned) {
    final Optional<Character> optPipe = get(position);
    if (optPipe.isEmpty()) {
      throw new IllegalArgumentException("Impossible position reached");
    }
    final char pipe = optPipe.get();
    Coordinate next = null;
    if (pipe == '-') {
      next = choose(position.west(), position.east(), banned);
    } else if (pipe == '|') {
      next = choose(position.north(), position.south(), banned);
    } else if (pipe == 'J') {
      next = choose(position.north(), position.west(), banned);
    } else if (pipe == 'L') {
      next = choose(position.north(), position.east(), banned);
    } else if (pipe == '7') {
      next = choose(position.south(), position.west(), banned);
    } else if (pipe == 'F') {
      next = choose(position.south(), position.east(), banned);
    }
    return next;
  }

  private Coordinate choose(final Coordinate coordinate1, final Coordinate coordinate2, final Coordinate banned) {
    return coordinate1.equals(banned)
           ? coordinate2
           : coordinate1;
  }

  public int count() {
    Coordinate route1Previous = start;
    Coordinate route2Previous = start;
    final List<Coordinate> currents = firstSteps;
    Coordinate route1Current = currents.get(0);
    Coordinate route2Current = currents.get(1);
    int count = 1;
    while (!route1Current.equals(route2Current)) {
      count++;
      final Coordinate route1Next = nextPosition(route1Current, route1Previous);

      route1Previous = route1Current;
      route1Current = route1Next;

      final Coordinate route2Next = nextPosition(route2Current, route2Previous);

      route2Previous = route2Current;
      route2Current = route2Next;
    }
    return count;
  }

  /**
   * All coordinates in loop (except horizontals).
   * @return
   */
  public Set<Coordinate> loopCoordinates() {
    final Set<Coordinate> coordinates = new HashSet<>();
    Coordinate routePrevious = start;
    Coordinate routeCurrent = firstSteps.get(0);
    addIfPresent(coordinates, routeCurrent);
    while (!routeCurrent.equals(start)) {
      final Coordinate routeNext = nextPosition(routeCurrent, routePrevious);
      routePrevious = routeCurrent;
      routeCurrent = routeNext;

      addIfPresent(coordinates, routeCurrent);
    }
    return Collections.unmodifiableSet(coordinates);
  }

  private void addIfPresent(final Set<Coordinate> coordinates, final Coordinate routeCurrent) {
    final Optional<Character> currentCharacter = get(routeCurrent);
    if (currentCharacter.isPresent()) {
      coordinates.add(routeCurrent);
    }
  }

  public boolean insideLoop(Coordinate coordinate, final Set<Coordinate> coordinates) {
    if (coordinates.contains(coordinate)) return false;

    int count = 0;
    boolean partialUp = false;
    boolean partialDown = false;
    while(coordinate.column() > 0) {
      coordinate = coordinate.west();
      if (coordinates.contains(coordinate)) {
        final Optional<Character> optionalCharacter = get(coordinate);
        if (optionalCharacter.isPresent()) {
          final char character = optionalCharacter.get();
          if (character == '|') {
            count++;
          } else if (character != '-') {
            if (character == 'F' || character == '7') {
              if (partialUp) {
                partialUp = false;
                count++;
              } else {
                partialDown = !partialDown;
              }
            }
            if (character == 'J' || character == 'L') {
              if (partialDown) {
                partialDown = false;
                count++;
              } else {
                partialUp = !partialUp;
              }
            }
          }
        }
      }
    }
    return count % 2 == 1;
  }

  public int countInside() {
    final Set<Coordinate> coordinates = loopCoordinates();
    int count = 0;
    for (int row = 0; row < lines.size(); row++) {
      for (int column = 0; column < lines.size(); column++) {
        if (insideLoop(new Coordinate(row, column), coordinates)) {
          count++;
        }
      }
    }
    return count;
  }
}
