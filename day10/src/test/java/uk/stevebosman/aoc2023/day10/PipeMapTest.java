package uk.stevebosman.aoc2023.day10;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    assertEquals(4, instance.count());
    assertEquals(Set.of(new Coordinate(1, 1),
                        new Coordinate(1,2),
                        new Coordinate(1,3),
                        new Coordinate(2,3),
                        new Coordinate(3,3),
                        new Coordinate(3,2),
                        new Coordinate(3,1),
                        new Coordinate(2,1)
                 ),
                 instance.loopCoordinates());

    assertAll(
            ()->assertFalse(instance.insideLoop(new Coordinate(2,0), instance.loopCoordinates())),
            ()->assertFalse(instance.insideLoop(new Coordinate(2,1), instance.loopCoordinates())),
            ()->assertTrue(instance.insideLoop(new Coordinate(2,2), instance.loopCoordinates())),
            ()->assertFalse(instance.insideLoop(new Coordinate(2,3), instance.loopCoordinates())),
            ()->assertFalse(instance.insideLoop(new Coordinate(2,4), instance.loopCoordinates()))
    );
    assertEquals(1, instance.countInside());
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
    assertEquals(4, instance.count());
    assertEquals(1, instance.countInside());
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
    assertEquals(8, instance.count());
    assertEquals(1, instance.countInside());
  }

  @Test
  void mapItComplex2() {
    // Given
    final String input = """
            ...........
            .S-------7.
            .|F-----7|.
            .||.....||.
            .||.....||.
            .|L-7.F-J|.
            .|..|.|..|.
            .L--J.L--J.
            ...........""";
    final PipeMap instance = new PipeMap(input);
    // When
    assertEquals(4, instance.countInside());
  }
}
