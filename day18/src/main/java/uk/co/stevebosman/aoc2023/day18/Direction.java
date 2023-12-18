package uk.co.stevebosman.aoc2023.day18;

public enum Direction {
  Up(0, -1), Down(0, 1), Left(-1, 0), Right(1, 0);
  private final int dx;
  private final int dy;

  Direction(final int dx, final int dy) {
    this.dx = dx;
    this.dy = dy;
  }

  public static Direction of(final String abbreviation) {
    return switch (abbreviation) {
      case "U" -> Up;
      case "D" -> Down;
      case "L" -> Left;
      case "R" -> Right;
      default -> throw new IllegalArgumentException("Unknown abbreviation: " + abbreviation);
    };
  }

  public int getDx() {
    return dx;
  }

  public int getDy() {
    return dy;
  }
}
