package uk.co.stevebosman.aoc1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.co.stevebosman.aoc1.AoC1;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AoC1Test {
  @ParameterizedTest
  @CsvSource(value = {
          "1abc2,12",
          "pqr3stu8vwx,38",
          "a1b2c3d4e5f,15",
          "treb7uchet,77"
  })
  void calibrateLine(final String line, final int expectedValue) {
    final int actual = new AoC1().calibrateLine(line);
    assertEquals(expectedValue, actual);
  }

  @Test
  void calibrateShortDocument() {
    final String document = "1abc2";

    final int actual = new AoC1().calibrateDocument(document.lines());
    assertEquals(12, actual);
  }

  @Test
  void calibrateLongerDocument() {
    final String document = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
            """;

    final int actual = new AoC1().calibrateDocument(document.lines());
    assertEquals(142, actual);
  }
}