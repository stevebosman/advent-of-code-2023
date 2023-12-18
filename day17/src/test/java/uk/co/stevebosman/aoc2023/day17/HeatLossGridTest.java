package uk.co.stevebosman.aoc2023.day17;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class HeatLossGridTest {

  @ParameterizedTest
  @CsvSource(value = {
          "1,3,102",
          "4,10,94",
  })
  void minimumHeatLossExample(final int min, final int max, final int expected) {
    // Given
    final var input = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533""";
    // When
    final var actual = HeatLossGrid.minimumHeatLoss(input.lines().toList(),min,max);
    // Then
    assertEquals(expected, actual);
  }

  @Test
  void minimumHeatLossExample2() {
    // Given
    final var input = """
            111111111111
            999999999991
            999999999991
            999999999991
            999999999991""";
    // When
    final var actual = HeatLossGrid.minimumHeatLoss(input.lines().toList(),4,10);
    // Then
    assertEquals(71, actual);
  }

  @ParameterizedTest
  @CsvSource(value = {
          "24\\n32, 5",
          "12\\n11, 2",
          "122\\n122\\n111, 4",
          "1222\\n1222\\n1222\\n1111, 6",
          "12222\\n12222\\n12222\\n12222\\n11111, 9",
  })
  void minimumHeatLossExamples(final String input, final int expected) {
    // When
    final var actual = HeatLossGrid.minimumHeatLoss(input.replaceAll("\\\\n", "\n").lines().toList(), 1, 3);
    // Then
    assertEquals(expected, actual);
  }
}