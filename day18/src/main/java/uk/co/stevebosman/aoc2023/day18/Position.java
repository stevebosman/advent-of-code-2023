package uk.co.stevebosman.aoc2023.day18;

public record Position(int x, int y) {
  public Position move(final Direction d) {
    return new Position(x + d.getDx(), y + d.getDy());
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
