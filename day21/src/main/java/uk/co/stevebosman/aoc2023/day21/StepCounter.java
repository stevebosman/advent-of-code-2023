package uk.co.stevebosman.aoc2023.day21;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StepCounter {
  private final List<List<String>> grid;

  public StepCounter(final Stream<String> lines) {
    grid = lines.map(this::of)
                .toList();
  }

  public List<String> of(final String line) {
    return new ArrayList<>(Arrays.stream(line.split(""))
                               .toList());
  }

  public Position getStart() {
    for (int y = 0; y < grid.size(); y++) {
      final List<String> row = grid.get(y);
      for (int x = 0; x < row.size(); x++) {
        if ("S".equals(row.get(x))) {
          return new Position(x, y);
        }
      }
    }
    throw new IllegalStateException("No start found");
  }

  public int count(final int max) {
    final int oddEven = max % 2;
    int count = 1 - oddEven;
    int check = 1;
    final var queue = new ArrayDeque<Position>();
    queue.add(getStart());
    while (!queue.isEmpty()) {
      final Position p = queue.removeFirst();
      final var here = get(p);
      if (here.isPresent()) {
        final int hereI = "S".equals(here.get())
                          ? 0
                          : Integer.parseInt(here.get());
        if (hereI == max) break;
        if (hereI == check) {
          check++;
        }
        for (final Direction d : Direction.values()) {
          final var newPosition = p.move(d);
          final var newValue = get(newPosition);
          if (newValue.isPresent() && ".".equals(newValue.get())) {
            queue.add(newPosition);
            set(newPosition, check);
            if (check % 2 == oddEven) {
              count++;
            }
          }
        }
      }
    }
    return count;
  }

  private Optional<String> get(final Position p) {
    try {
      return Optional.of(grid.get(p.y())
                 .get(p.x()));
    } catch (final Exception e) {
      return Optional.empty();
    }
  }

  private void set(final Position p, final int i) {
    grid.get(p.y()).set(p.x(), String.valueOf(i));
  }
}
