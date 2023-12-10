package uk.co.stevebosman.aoc2023.day2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GamePowerCalculatorTest {
  private final GamePowerCalculator instance = new GamePowerCalculator();

  @Test
  void totalPower() {
    final String input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """;

    assertEquals(2286, instance.totalPower(input.lines()));
  }

  @ParameterizedTest
  @CsvSource(value = {
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green|48",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue|12",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red|1560",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red|630",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green|36"
  }, delimiter = '|')
  void verifyGame(final String line, final int expected) {
    final int actual = instance.gamePower(line);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource(value = {
          "3 blue, 4 red|4|0|3",
          "1 red, 2 green, 6 blue|1|2|6",
          "2 green|0|2|0",
          "1 blue, 2 green|0|2|1",
          "3 green, 4 blue, 1 red|1|3|4",
          "1 green, 1 blue|0|1|1",
          "8 green, 6 blue, 20 red|20|8|6",
          "5 blue, 4 red, 13 green|4|13|5",
          "5 green, 1 red|1|5|0",
          "1 green, 3 red, 6 blue|3|1|6",
          "3 green, 6 red|6|3|0",
          "3 green, 15 blue, 14 red|14|3|15",
          "6 red, 1 blue, 3 green|6|3|1",
          "2 blue, 1 red, 2 green|1|2|2"
  }, delimiter = '|')
  void countColors(final String round, final int expectedRed, final int expectedGreen, final int expectedBlue) {
    final CubeCounts actual = instance.countColors(round);
    assertEquals(expectedRed, actual.red());
    assertEquals(expectedGreen, actual.green());
    assertEquals(expectedBlue, actual.blue());
  }
}
