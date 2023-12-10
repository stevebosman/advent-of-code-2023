package uk.co.stevebosman.aoc2023.day2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GameVerifierTest {
  private final GameVerifier instance = new GameVerifier();

  @Test
  void verifyGames() {
    final String input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """;

    assertEquals(8, instance.verifyGames(input.lines()));
  }

  @ParameterizedTest
  @CsvSource(value = {
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green|1",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue|2",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red|0",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red|0",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green|5"
  }, delimiter = '|')
  void verifyGame(final String line, final int expected) {
    final int actual = instance.verifyGame(line, 12,13,14);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource(value = {
          "3 blue, 4 red|true",
          "1 red, 2 green, 6 blue|true",
          "2 green|true",
          "1 blue, 2 green|true",
          "3 green, 4 blue, 1 red|true",
          "1 green, 1 blue|true",
          "8 green, 6 blue, 20 red|false",
          "5 blue, 4 red, 13 green|true",
          "5 green, 1 red|true",
          "1 green, 3 red, 6 blue|true",
          "3 green, 6 red|true",
          "3 green, 15 blue, 14 red|false",
          "6 red, 1 blue, 3 green|true",
          "2 blue, 1 red, 2 green|true"
  }, delimiter = '|')
  void verifyRound(final String round, final boolean expected) {
    final boolean actual = instance.verifyRound(round, 12,13,14);
    assertEquals(expected, actual);
  }
}
