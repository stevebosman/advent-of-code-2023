package uk.stevebosman.aoc2023.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RangeTest {
  @ParameterizedTest
  @CsvSource(value = {
          "50 98 2, 98, 99, 50"
  })
  void testCanCreateRange(final String line, final long fromStart, final long fromEnd, final long toStart) {
    // When
    final Range actual = Range.of(line);
    // Then
    assertEquals(fromStart, actual.fromStart());
    assertEquals(fromEnd, actual.fromEnd());
    assertEquals(toStart, actual.toStart());
  }
}
