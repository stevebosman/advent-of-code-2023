package uk.co.stevebosman.aoc2023.day18;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrenchGrid {
  private final List<Position> trench;
  private final int minX;
  private final int maxX;
  private final int minY;
  private final int maxY;
  public TrenchGrid(final List<Position> trench) {
    this.trench = trench;

    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxY = Integer.MIN_VALUE;
    for (Position p: trench) {
      minX = Math.min(minX, p.x());
      minY = Math.min(minY, p.y());
      maxX = Math.max(maxX, p.x());
      maxY = Math.max(maxY, p.y());
    }
    this.minX = minX;
    this.maxX = maxX;
    this.minY = minY;
    this.maxY = maxY;

  }

  public static TrenchGrid of(final List<String> lines) {
    final var holes = new ArrayList<Position>();
    var position = new Position(0,0);
    holes.add(position);
    for (String line: lines) {
      final var move = Move.of(line);
      for (int i = 0; i < move.length(); i++) {
        position = position.move(move.direction());
        holes.add(position);
      }
    }

    return new TrenchGrid(holes);
  }

  public String toString() {
    return stringify(trench);
  }

  private String stringify(Collection<Position> positions) {
    final StringBuilder sb = new StringBuilder();
    for (int y = minY; y < maxY+1; y++) {
      for (int x = minX; x <maxX+1; x++) {
        if (x==0 && y==0) {
          sb.append("*");
        } else {
          if (positions.contains(new Position(x, y))) {
            sb.append("#");
          } else {
            sb.append(".");
          }
        }
      }
      if (y < maxY) sb.append("|\n");
    }
    return sb.toString();
  }

  public int countHole() {
    final var outside = new HashSet<Position>();
    final var checked = new HashSet<Position>();

    final int gridSize = (1 + maxY - minY) * (1 + maxX - minX);

    for (int y = minY; y < maxY+1; y++) {
      Position position = new Position(minX, y);
      checkIfOutside(outside, checked, position);

      position = new Position(maxX, y);
      checkIfOutside(outside, checked, position);
    }
    for (int x = minX + 1; x < maxX; x++) {
      Position position = new Position(x, minY);
      checkIfOutside(outside, checked, position);

      position = new Position(x, maxY);
      checkIfOutside(outside, checked, position);
    }
    return gridSize - outside.size();
  }

  private void checkIfOutside(
          Set<Position> outside,
          Set<Position> checked,
          Position start
  ) {
    if (trench.contains(start) || checked.contains(start)) return;

    final var checks = new ArrayDeque<Position>();
    checks.add(start);

    while(!checks.isEmpty()) {
      final var position = checks.removeFirst();
      checked.add(position);
      if (!trench.contains(position) && !outside.contains(position)) {
        outside.add(position);
        for (Direction d: Direction.values()) {
          final var nextPosition = position.move(d);
          if (!checked.contains(nextPosition)
                  && hasCell(nextPosition)) {
            checks.add(nextPosition);
          }
        }
      }
    }
  }

  private boolean hasCell(Position nextPosition) {
    return nextPosition.x() >= minX && nextPosition.x() <= maxX
            && nextPosition.y() >= minY && nextPosition.y() <= maxY;
  }
}
