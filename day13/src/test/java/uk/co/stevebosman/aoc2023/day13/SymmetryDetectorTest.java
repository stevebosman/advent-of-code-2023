package uk.co.stevebosman.aoc2023.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymmetryDetectorTest {
  @Test
  void test() {
    final String input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
                        
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#""";
    final SymmetryDetector instance = new SymmetryDetector(input);
    assertEquals(2, instance.size());
    assertAll(
            () -> assertEquals(5, instance.process(0)),
            () -> assertEquals(400, instance.process(1))
    );
    assertEquals(405, instance.processAll());
  }

}