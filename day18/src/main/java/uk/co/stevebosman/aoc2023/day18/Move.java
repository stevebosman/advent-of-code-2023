package uk.co.stevebosman.aoc2023.day18;

public record Move(Direction direction, int length) {
  public static Move of(final String line) {
    var parts = line.split(" ");
    return new Move(Direction.of(parts[0]), Integer.parseInt(parts[1]));
  }
}
