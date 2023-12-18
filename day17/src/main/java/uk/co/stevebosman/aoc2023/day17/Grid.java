package uk.co.stevebosman.aoc2023.day17;

import java.util.List;
import java.util.Optional;

final class Grid {
  private final int height;
  private final int width;
  private final List<List<Integer>> grid;

  public Grid(final List<String> lines) {
    this.height = lines.size();
    this.width = lines.get(0)
                      .length();
    this.grid = lines.stream()
                     .map(l -> l.codePoints()
                                .mapToObj(i -> i - '0')
                                .toList())
                     .toList();
  }

  public int getMaxY() {
    return height - 1;
  }

  public int getMaxX() {
    return width - 1;
  }

  public Optional<Integer> get(final Position p) {
    if (p.x() >= 0 && p.x() < width && p.y() >= 0 && p.y() < height) {
      return Optional.of(grid.get(p.y())
                             .get(p.x()));
    }
    return Optional.empty();
  }
}
