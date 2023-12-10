package uk.stevebosman.aoc2023.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PipeMapTest {
  @Test
  void mapItSquare() {
    // Given
    final String input = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....""";
    final PipeMap instance = new PipeMap(input);
    // When
    final Coordinate start = instance.getStart();
    assertEquals(new Coordinate(1,1), start);
    assertEquals(4, instance.count());
  }

  @Test
  void mapItWithExtras() {
    // Given
    final String input = """
            -L|F7
            7S-7|
            L|7||
            -L-J|
            L|-JF""";
    final PipeMap instance = new PipeMap(input);
    // When
    final Coordinate start = instance.getStart();
    assertEquals(new Coordinate(1,1), start);
    assertEquals(4, instance.count());
  }


  @Test
  void mapItComplex() {
    // Given
    final String input = """
            ..F7.
            .FJ|.
            SJ.L7
            |F--J
            LJ...""";
    final PipeMap instance = new PipeMap(input);
    // When
    final Coordinate start = instance.getStart();
    assertEquals(new Coordinate(2,0), start);
    assertEquals(8, instance.count());
  }
}
