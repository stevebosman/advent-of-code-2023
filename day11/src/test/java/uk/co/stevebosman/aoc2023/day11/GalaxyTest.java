package uk.co.stevebosman.aoc2023.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GalaxyTest {
  @ParameterizedTest
  @CsvSource(value = {
          "1,6,5,11,9",
          "4,0,9,10,15",
          "0,2,12,7,17",
          "0,11,5,11,5",
  })
  void manhattan(final int x1, final int y1, final int x2, final int y2, final int expected) {
    assertEquals(expected, new Galaxy(x1, y1).manhattanDistance(new Galaxy(x2, y2)));
  }
}
