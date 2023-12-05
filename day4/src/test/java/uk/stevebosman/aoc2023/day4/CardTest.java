package uk.stevebosman.aoc2023.day4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

  @Test
  void testOfFactory1() {
    // Given
    final String line = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
    // When
    final Card actual = Card.of(line);
    // Then
    assertEquals(1, actual.id());
    assertEquals(Set.of(41, 48, 83, 86, 17), actual.winners());
    assertEquals(Set.of(83, 86,  6, 31, 17,  9, 48, 53), actual.have());
    assertEquals(8, actual.score());
  }

  @Test
  void testOfFactory2() {
    // Given
    final String line = "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19";
    // When
    final Card actual = Card.of(line);
    // Then
    assertEquals(2, actual.id());
    assertEquals(Set.of(13, 32, 20, 16, 61), actual.winners());
    assertEquals(Set.of(61, 30, 68, 82, 17, 32, 24, 19), actual.have());
    assertEquals(2, actual.score());
  }

}
