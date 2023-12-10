package uk.stevebosman.aoc2023.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PipeMap {
  final List<String> lines;

  public PipeMap(final String input) {
    this.lines = input.lines()
                      .toList();
  }

  public Coordinate getStart() {
    for (int row = 0; row < lines.size(); row++) {
      final int column = lines.get(row)
                              .indexOf('S');
      if (column != -1) {
        return new Coordinate(row, column);
      }
    }
    throw new IllegalArgumentException("no starting position");
  }

  public List<Coordinate> getStarts() {
    final Coordinate start = getStart();
    final List<Coordinate> result = new ArrayList<>();
    addIfConnected(result, start.north(), Set.of('|', '7', 'F'));
    addIfConnected(result, start.south(), Set.of('|', 'L', 'J'));
    addIfConnected(result, start.east(), Set.of('-', '7', 'J'));
    addIfConnected(result, start.west(), Set.of('-', 'L', 'F'));

    return result;
  }

  private void addIfConnected(final List<Coordinate> result, final Coordinate position, final Set<Character> connectable) {
    final Optional<Character> easterly  = get(position);
    if (easterly.isPresent()) {
      if (connectable.contains(easterly.get())) {
        result.add(position);
      }
    }
  }

  public Optional<Character> get(final Coordinate coordinate) {
    if (coordinate.row() < 0 || coordinate.row() >= lines.size() || coordinate.column() < 0 || coordinate.column() >= lines.get(0).length()) {
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
    System.out.println("position = " + position + ", pipe = " + pipe);
    Coordinate next = null;
    if (pipe == '-') {
      next = choose(new Coordinate(position.row(), position.column() - 1),
                    new Coordinate(position.row(), position.column() + 1),
                    banned);
    } else if (pipe == '|') {
      next = choose(new Coordinate(position.row() - 1, position.column()),
                    new Coordinate(position.row() + 1, position.column()),
                    banned);
    } else if (pipe == 'J') {
      next = choose(new Coordinate(position.row() - 1, position.column()),
                    new Coordinate(position.row(), position.column() - 1),
                    banned);
    } else if (pipe == 'L') {
      next = choose(new Coordinate(position.row() - 1, position.column()),
                    new Coordinate(position.row(), position.column() + 1),
                    banned);
    } else if (pipe == 'F') {
      next = choose(new Coordinate(position.row() + 1, position.column()),
                    new Coordinate(position.row(), position.column() + 1),
                    banned);
    } else if (pipe == '7') {
      next = choose(new Coordinate(position.row(), position.column() - 1),
                    new Coordinate(position.row() + 1, position.column()),
                    banned);
    }
    return next;
  }

  private Coordinate choose(final Coordinate coordinate1, final Coordinate coordinate2, final Coordinate banned) {
    return coordinate1.equals(banned)
           ? coordinate2
           : coordinate1;
  }

  public int count() {
    Coordinate route1Previous = getStart();
    Coordinate route2Previous = getStart();
    final List<Coordinate> currents = getStarts();
    Coordinate route1Current = currents.get(0);
    Coordinate route2Current = currents.get(1);
    int count = 1;
    while (!route1Current.equals(route2Current)) {
      System.out.println(route1Current + " " + route2Current);
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
}
