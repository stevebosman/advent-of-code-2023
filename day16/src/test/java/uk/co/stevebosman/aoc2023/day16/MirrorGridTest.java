package uk.co.stevebosman.aoc2023.day16;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MirrorGridTest {
  @Test
  void mirrorGrid() {
    // Given
    final var input = """
            .|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....""";
    // When
    final var instance = new MirrorGrid(input.lines().toList());
    // Then
    assertEquals(10, instance.getHeight());
    assertEquals(10, instance.getWidth());
    final Set<BeamType> beamTypes = instance.getBeamTypes(0, 0);
    assertEquals(1, beamTypes.size());
    assertEquals(BeamType.LeftToRight, beamTypes.stream().findFirst().orElse(null));
    assertTrue(instance.getBeamTypes(1,1).isEmpty());
    assertEquals("""
            >|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....""",instance.toString());
  }

  @Test
  void iterate1() {
    // Given
    final var input = """
            .|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....""";
    final var initial = new MirrorGrid(input.lines().toList());
    // When
    final var instance = initial.iterate();
    // Then
    assertEquals("""
            >|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....""",instance.toString());
  }

  @Test
  void iterate2() {
    // Given
    final var input = """
            .|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....""";
    final var initial = new MirrorGrid(input.lines().toList());
    final var iterate1 = initial.iterate();
    // When
    final var instance = iterate1.iterate();
    // Then
    assertEquals("""
            >|...\\....
            |v-.\\.....
            .v...|-...
            .v......|.
            .v........
            .v.......\\
            .v../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....""",instance.toString());
  }


  @Test
  void iterateAll() {
    // Given
    final var input = """
            .|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....""";
    final var initial = new MirrorGrid(input.lines().toList());
    // When
    final var instance = MirrorGrid.iterateAll(initial);
    // Then
    assertEquals("""
                         >|<<<\\....
                         |v-.\\^....
                         .v...|->>>
                         .v...v^.|.
                         .v...v^...
                         .v...v^..\\
                         .v../2\\\\..
                         <->-/vv|..
                         .|<<<2-|.\\
                         .v//.|.v..""", instance.toString());
    assertEquals("""
                         ######....
                         .#...#....
                         .#...#####
                         .#...##...
                         .#...##...
                         .#...##...
                         .#..####..
                         ########..
                         .#######..
                         .#...#.#..""", instance.toEnergyString());
    assertEquals(46, instance.getCurrentEnergy());
  }

  @Test
  void getMaxEnergy() {
    // Given
    final var input = """
            .|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....""";
    // When
    final long actual = MirrorGrid.getMaxEnergy(input);
    // Then
    assertEquals(51, actual);
  }
}
