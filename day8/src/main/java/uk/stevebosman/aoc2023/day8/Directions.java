package uk.stevebosman.aoc2023.day8;

import java.util.List;

public class Directions {
  private final List<Direction> directions;
  private int current;

  public Directions(final String input) {
    directions = input.replaceAll(" ", "").codePoints().boxed().map(Direction::of).toList();
    current = 0;
  }

  public Direction next() {
    final Direction result = directions.get(current);
    current++;
    if (current==directions.size()) {
      current = 0;
    }
    return result;
  }

  public List<Direction> getDirections() {
    return directions;
  }


  public enum Direction {
    Left, Right;

    private static Direction of(final int c) {
      return c == 'R'
             ? Direction.Right
             : Direction.Left;
    }
  }
}
