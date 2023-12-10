package uk.co.stevebosman.aoc2023.day6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BoatDistanceCalculatorTest {
  private final BoatDistanceCalculator instance = new BoatDistanceCalculator();

  @ParameterizedTest
  @CsvSource(value = {
          "0, 7, 0",
          "1, 7, 6",
          "2, 7, 10",
          "3, 7, 12",
          "4, 7, 12",
          "5, 7, 10",
          "6, 7, 6",
          "7, 7, 0",
  })
  public void distanceTravelled(final long speed, final long totalTimeAvailable, final long expectedDistance) {
    assertEquals(expectedDistance, instance.distanceTravelled(speed, totalTimeAvailable));
  }

  @ParameterizedTest
  @CsvSource(value = {
          "7, 9, 4",
          "15, 40, 8",
          "30, 200, 9",
  })
  public void potentialVictoriesCount(final long totalTimeAvailable, final long distance, final long expectedCount) {
    assertEquals(expectedCount, instance.potentialVictoriesCount(new Race(totalTimeAvailable, distance)));
  }

  @Test
  public void victoryFactor() {
    final String input = """
Time:      7  15   30
Distance:  9  40  200
            """;
    assertEquals(4*8*9, instance.victoryFactor(input));
  }
}