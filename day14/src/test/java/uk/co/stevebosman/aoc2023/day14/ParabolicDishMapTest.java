package uk.co.stevebosman.aoc2023.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParabolicDishMapTest {
  @Test
  void tiltNorthWeight() {
    final String input = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....""";

    // When
    final ParabolicDishMap instance = new ParabolicDishMap(input);

    // Then
    assertEquals(136, instance.tiltNorthWeight());

  }

  @Test
  void weight() {
    final String input = """
            OOOO.#.O..
            OO..#....#
            OO..O##..O
            O..#.OO...
            ........#.
            ..#....#.#
            ..O..#.O.O
            ..O.......
            #....###..
            #....#....""";

    // When
    final int actual = new ParabolicDishMap(input).weight();

    // Then
    assertEquals(136, actual);

  }

  @Test
  void tiltCycle1() {
    final String input = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....""";

    // When
    final ParabolicDishMap instance = new ParabolicDishMap(input);

    // Then
    final String expected = """
            .....#....
            ....#...O#
            ...OO##...
            .OO#......
            .....OOO#.
            .O#...O#.#
            ....O#....
            ......OOOO
            #...O###..
            #..OO#....""";
    assertEquals(expected, instance.tiltCycle(1));

  }
}