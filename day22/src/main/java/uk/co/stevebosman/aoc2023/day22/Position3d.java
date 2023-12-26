package uk.co.stevebosman.aoc2023.day22;

import java.util.Comparator;

public record Position3d(int x, int y, int z) implements Comparable<Position3d> {
  public static final Comparator<Position3d> TOP_DOWN = (o1, o2) -> {
    int result = o2.z - o1.z;
    if (result == 0) result = o1.x - o2.x;
    if (result == 0) result = o1.y - o2.y;
    return result;
  };

  public static Position3d of(final String input) {
    final var values = input.split(",");
    return new Position3d(
            Integer.parseInt(values[0]),
            Integer.parseInt(values[1]),
            Integer.parseInt(values[2])
    );
  }

  @Override
  public int compareTo(final Position3d o) {
    int result = z - o.z;
    if (result == 0) result = x - o.x;
    if (result == 0) result = y - o.y;
    return result;
  }

  public String toString() {
    return "(" + x + "," + y + "," + z + ")";
  }
}
