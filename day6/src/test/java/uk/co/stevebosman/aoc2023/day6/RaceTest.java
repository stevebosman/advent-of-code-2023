package uk.co.stevebosman.aoc2023.day6;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceTest {
  @Test
  public void of() {
    final String input = """
            Time:      7  15   30
            Distance:  9  40  200""";
    final List<Race> expected = List.of(
            new Race(7, 9),
            new Race(15, 40),
            new Race(30, 200));
    assertEquals(expected, Race.of(input));
  }

}
