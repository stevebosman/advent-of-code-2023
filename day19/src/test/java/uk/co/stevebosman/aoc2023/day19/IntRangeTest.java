package uk.co.stevebosman.aoc2023.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntRangeTest {
  @Test
  void test() {
    final var range = new IntRange(100,200);
    final var noOverlap = range.overlap(new IntRange(201,400));
    assertTrue(noOverlap.isEmpty());
    final var singleOverlap = range.overlap(new IntRange(200,400));
    assertTrue(singleOverlap.isPresent());
    assertEquals(200, singleOverlap.get().min());
    assertEquals(200, singleOverlap.get().max());
    final var overlapBelow = range.overlap(new IntRange(98,102));
    assertTrue(overlapBelow.isPresent());
    assertEquals(100, overlapBelow.get().min());
    assertEquals(102, overlapBelow.get().max());
    final var fullOverlap = range.overlap(new IntRange(200,400));
    assertTrue(fullOverlap.isPresent());
    assertEquals(singleOverlap, fullOverlap);
  }
}
