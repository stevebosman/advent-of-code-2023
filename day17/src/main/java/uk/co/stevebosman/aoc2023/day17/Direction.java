package uk.co.stevebosman.aoc2023.day17;

enum Direction {
  North(0, -1), South(0, 1), East(1, 0), West(-1, 0);
  private final int dx;
  private final int dy;

  Direction(final int dx, final int dy) {
    this.dx = dx;
    this.dy = dy;
  }

  public int getDx() {
    return dx;
  }

  public int getDy() {
    return dy;
  }

  public Direction opposite() {
    return switch (this) {
      case North -> South;
      case South -> North;
      case East -> West;
      case West -> East;
    };
  }
}
