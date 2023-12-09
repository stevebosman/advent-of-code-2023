package uk.stevebosman.aoc2023.day9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HistoryTest {
  @Test
  void canConstructHistory() {
    // Given
    final String input = "0 3 6 9 12 15";
    // When
    final History actual = new History(input);
    // Then
    assertEquals(List.of(0, 3, 6, 9, 12, 15), actual.getValues());
  }

  @Test
  void canConstructSubHistory() {
    // Given
    final List<Integer> input = List.of(0, 3, 6, 9, 12, 15);
    final History instance = new History(input);
    // When
    final History actual = instance.getSubHistory();
    // Then
    assertEquals(List.of(3, 3, 3, 3, 3), actual.getValues());
  }

  @Test
  void canConstructSubHistory2() {
    // Given
    final List<Integer> input = List.of(1, 3, 6, 10, 15, 21);
    final History instance = new History(input);
    // When
    final History actual = instance.getSubHistory();
    // Then
    assertEquals(List.of(2, 3, 4, 5, 6), actual.getValues());
  }

  @Test
  void subHistoryEmptyWhenConstant() {
    // Given
    final List<Integer> input = List.of(3, 3, 3, 3);
    final History instance = new History(input);
    // When
    final History actual = instance.getSubHistory();
    // Then
    assertTrue(actual.getValues()
                     .isEmpty());
  }

  @Test
  void canCalculateNextWhenConstant() {
    // Given
    final List<Integer> input = List.of(3, 3, 3, 3);
    final History instance = new History(input);
    // When
    final int actual = instance.next();
    // Then
    assertEquals(3, actual);
  }

  @Test
  void canCalculateNextWhenExample1() {
    // Given
    final List<Integer> input = List.of(0, 3, 6, 9, 12, 15);
    final History instance = new History(input);
    // When
    final int actual = instance.next();
    // Then
    assertEquals(18, actual);
  }

  @Test
  void canCalculateNextWhenExample2() {
    // Given
    final List<Integer> input = List.of(1, 3, 6, 10, 15, 21);
    final History instance = new History(input);
    // When
    final int actual = instance.next();
    // Then
    assertEquals(28, actual);
  }

  @Test
  void canCalculateNextWhenExample3() {
    // Given
    final List<Integer> input = List.of(10, 13, 16, 21, 30, 45);
    final History instance = new History(input);
    // When
    final int actual = instance.next();
    // Then
    assertEquals(68, actual);
  }

  @Test
  void canSumNext() {
    final String input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45""";
    assertEquals(114, History.sumNext(input.lines()));
  }

  @Test
  void canCalculatePreviousWhenExample1() {
    // Given
    final List<Integer> input = List.of(0, 3, 6, 9, 12, 15);
    final History instance = new History(input);
    // When
    final int actual = instance.previous();
    // Then
    assertEquals(-3, actual);
  }

  @Test
  void canCalculatePreviousWhenExample2() {
    // Given
    final List<Integer> input = List.of(1, 3, 6, 10, 15, 21);
    final History instance = new History(input);
    // When
    final int actual = instance.previous();
    // Then
    assertEquals(0, actual);
  }

  @Test
  void canCalculatePreviousWhenExample3() {
    // Given
    final List<Integer> input = List.of(10, 13, 16, 21, 30, 45);
    final History instance = new History(input);
    // When
    final int actual = instance.previous();
    // Then
    assertEquals(5, actual);
  }

  @Test
  void canSumPrevious() {
    final String input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45""";
    assertEquals(2, History.sumPrevious(input.lines()));
  }
}
