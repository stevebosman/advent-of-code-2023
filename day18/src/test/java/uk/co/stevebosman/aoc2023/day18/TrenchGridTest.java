package uk.co.stevebosman.aoc2023.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrenchGridTest {

  public static final String EXAMPLE_PART_1 = """
          R 6 (#70c710)
          D 5 (#0dc571)
          L 2 (#5713f0)
          D 2 (#d2c081)
          R 2 (#59c680)
          D 2 (#411b91)
          L 5 (#8ceee2)
          U 2 (#caa173)
          L 1 (#1b58a2)
          U 2 (#caa171)
          R 2 (#7807d2)
          U 3 (#a77fa3)
          L 2 (#015232)
          U 2 (#7a21e3)""";

  @Test
  void buildTrench() {
    // When
    final var actual = TrenchGrid.of(EXAMPLE_PART_1.lines().toList());
    // Then
    final var expected= """
            #######
            #.....#
            ###...#
            ..#...#
            ..#...#
            ###.###
            #...#..
            ##..###
            .#....#
            .######""";
    assertEquals(expected, actual.toString());
  }

  @Test
  void fillInTrench() {
    // Given
    final var instance = TrenchGrid.of(EXAMPLE_PART_1.lines().toList());
    // When
    final var actual = instance.countHole();
    // Then
    assertEquals(62, actual);
  }
}