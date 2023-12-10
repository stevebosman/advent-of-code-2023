package uk.co.stevebosman.aoc2023.day10;

public record Coordinate(int row, int column) {
  public Coordinate north() {
    return new Coordinate(row - 1, column);
  }
  public Coordinate south() {
    return new Coordinate(row + 1, column);
  }
  public Coordinate east() {
    return new Coordinate(row, column + 1);
  }
  public Coordinate west() {
    return new Coordinate(row, column - 1);
  }
}
