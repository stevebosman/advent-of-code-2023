package uk.stevebosman.aoc2023.day3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EngineInspectorTest {

  @ParameterizedTest
  @CsvSource(value = {
          "467..114.., 0",
          "...*......, 0",
          "617*......, 617",
          ".664*598.., 1262",
  })
  void inspectSingleLine(final String input, final int expected) {
    final EngineInspector instance = new EngineInspector(input);

    assertEquals(expected, instance.inspect());
  }

  @ParameterizedTest
  @CsvSource(value = {
          "467..114..\\n...*......, 467",
  })
  void inspectTwoLines(final String input, final int expected) {
    final EngineInspector instance = new EngineInspector(input.replaceAll("\\\\n", "\n"));
    assertEquals(expected, instance.inspect());
  }


  @Test
  void inspect() {
    final String input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
            """;

    final EngineInspector instance = new EngineInspector(input);
    assertEquals(4361, instance.inspect());
  }

  @Test
  void sumGearRatios() {
    final String input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
            """;

    final EngineInspector instance = new EngineInspector(input);
    assertEquals(467835, instance.sumGearRatios());
  }
}
