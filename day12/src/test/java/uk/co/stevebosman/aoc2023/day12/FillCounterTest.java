package uk.co.stevebosman.aoc2023.day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FillCounterTest {

  @ParameterizedTest
  @CsvSource(value = {
          "???.### 1,1,3 1",
          ".??..??...?##. 1,1,3 4",
          "?#?#?#?#?#?#?#? 1,3,1,6 1",
          "????.#...#... 4,1,1 1",
          "????.######..#####. 1,6,5 4",
          "?###???????? 3,2,1 10",
  }, delimiter = ' ')
  void count(final String input, final String countsText, final long expected) {
    // Given
    final List<Integer> counts = Arrays.stream(countsText.split(","))
                                       .map(Integer::valueOf)
                                       .toList();
    // When
    final long actual = new FillCounter().count(input, counts);
    //Then
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource(value = {
          "???.### 1,1,3|1",
          ".??..??...?##. 1,1,3|16384",
          "?#?#?#?#?#?#?#? 1,3,1,6|1",
          "????.#...#... 4,1,1|16",
          "????.######..#####. 1,6,5|2500",
          "?###???????? 3,2,1|506250",
  }, delimiter = '|')
  void count2(final String input, final long expected) {
    // When
    final long actual = new FillCounter().count2(input);
    //Then
    assertEquals(expected, actual);
  }
}