package uk.co.stevebosman.aoc2023.day19;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTest {
  @ParameterizedTest
  @CsvSource(value = {
    "{x=331,m=185,a=1787,s=1397} | 331 | 185 | 1787 | 1397"
  }, delimiter = '|')
  void of(final String partInput,
          final int expectedX,
          final int expectedM,
          final int expectedA,
          final int expectedS) {
    final var part = Part.of(partInput);
    //
    assertAll(
            ()->assertEquals(expectedX, part.x()),
            ()->assertEquals(expectedM, part.m()),
            ()->assertEquals(expectedA, part.a()),
            ()->assertEquals(expectedS, part.s())
    );

  }
}
