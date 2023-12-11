package uk.co.stevebosman.aoc2023.day11;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GalaxyMapTest {
  @Test
  void expand() {
    final String input = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....""";

    final GalaxyMap galaxyMap = new GalaxyMap(input.lines()
                                             .toList(), 2);
    assertEquals(List.of(
            new Galaxy(4,0),
            new Galaxy(9,1),
            new Galaxy(0,2),
            new Galaxy(8,5),
            new Galaxy(1,6),
            new Galaxy(12,7),
            new Galaxy(9,10),
            new Galaxy(0,11),
            new Galaxy(5,11)
    ), galaxyMap.getGalaxies());

    assertEquals(374, galaxyMap.sumDistances());
  }
}
