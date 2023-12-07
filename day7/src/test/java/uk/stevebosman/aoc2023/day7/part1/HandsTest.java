package uk.stevebosman.aoc2023.day7.part1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandsTest {
  @Test
  void score0() {
    // Given
    final Hands instance = new Hands(List.of());
    // When
    final int actual = instance.score();
    // Then
    assertEquals(0, actual);
  }

  @Test
  void score1() {
    // Given
    final Hands instance = new Hands(List.of(
            Hand.of("32T3K 765")
    ));
    // When
    final int actual = instance.score();
    // Then
    assertEquals(765, actual);
  }

  @Test
  void score() {
    // Given
    final Hands instance = new Hands(List.of(
            Hand.of("32T3K 765"),
            Hand.of("T55J5 684"),
            Hand.of("KK677 28"),
            Hand.of("KTJJT 220"),
            Hand.of("QQQJA 483")
    ));
    // When
    final int actual = instance.score();
    // Then
    assertEquals(6440, actual);
  }


}
