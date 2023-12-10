package uk.co.stevebosman.aoc2023.day8;

import static uk.co.stevebosman.aoc2023.day8.Directions.Direction.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionsTest {
  @Test
  void of() {
    final Directions instance = new Directions("LRLRR");
    assertEquals(List.of(Left, Right, Left, Right, Right), instance.getDirections());
  }
}
