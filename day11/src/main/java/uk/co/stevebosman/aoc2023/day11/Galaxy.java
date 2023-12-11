package uk.co.stevebosman.aoc2023.day11;

public record Galaxy(int x, int y) {
  public int manhattanDistance(final Galaxy g) {
    return Math.abs(g.x - x) + Math.abs(g.y - y);
  }
}
