package uk.co.stevebosman.aoc2023.day17;

enum Direction {
  South(0, 1), North(0, -1), East(-1, 0), West(1, 0);
  private final int dy;
  private final int dx;

  Direction(final int dx, final int dy) {
    this.dy = dy;
    this.dx = dx;
  }

  public int getDy() {
    return dy;
  }

  public int getDx() {
    return dx;
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
