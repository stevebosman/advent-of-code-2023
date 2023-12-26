package uk.co.stevebosman.aoc2023.day22;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SandSlabsTest {
  @Test
  void example1() {
    final var input = """
            1,0,1~1,2,1
            0,0,2~2,0,2
            0,2,3~2,2,3
            0,0,4~0,2,4
            2,0,5~2,2,5
            0,1,6~2,1,6
            1,1,8~1,1,9""";
    final var instance = SandSlabs.of(input.lines().toList());
    assertEquals(5, instance.canDisintegrateCount());
  }

  @Test
  void example2() {
    final var input = """
            6,6,1~7,6,1
            7,6,2~9,6,2""";
    final var instance = SandSlabs.of(input.lines().toList());
    assertEquals(1, instance.canDisintegrateCount());
  }
}