package uk.co.stevebosman.aoc2023.day22;

public record Position(int x, int y) {
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
