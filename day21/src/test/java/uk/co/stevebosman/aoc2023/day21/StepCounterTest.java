package uk.co.stevebosman.aoc2023.day21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepCounterTest {
  @Test
  void count() {
    final var input = """
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........""";
    final var instance = new StepCounter(input.lines());
    assertEquals(16, instance.count(6));
  }
}
