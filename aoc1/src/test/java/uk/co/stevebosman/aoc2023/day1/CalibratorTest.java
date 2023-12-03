package uk.co.stevebosman.aoc2023.day1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalibratorTest {
  @ParameterizedTest
  @CsvSource(value = {
          "1abc2,12",
          "pqr3stu8vwx,38",
          "a1b2c3d4e5f,15",
          "treb7uchet,77"
  })
  void calibrateLine(final String line, final int expectedValue) {
    final int actual = new Calibrator().calibrateLine(line);
    assertEquals(expectedValue, actual);
  }

  @ParameterizedTest
  @CsvSource(value = {
          "two1nine,2wo19ine",
          "eightwothree,8igh2wo3hree",
          "abcone2threexyz,abc1ne23hreexyz",
          "xtwone3four,x2w1ne34our",
          "4nineeightseven2,49ine8ight7even2",
          "zoneight234,z1n8ight234",
          "7pqrstsixteen,7pqrst6ixteen",
  })
  void fixLine(final String line, final String expected) {
    final String actual = new Calibrator().fixLine(line);
    assertEquals(expected, actual);
  }

  @Test
  void calibrateShortDocument() {
    final String document = "1abc2";

    final int actual = new Calibrator().calibrateDocument(document.lines());
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

    final int actual = new Calibrator().calibrateDocument(document.lines());
    assertEquals(142, actual);
  }

  @Test
  void calibrateShortFixedDocument() {
    final String document = "eightwothree";

    final int actual = new Calibrator().calibrateFixedDocument(document.lines());
    assertEquals(83, actual);
  }

  @Test
  void calibrateLongerFixedDocument() {
    final String document = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
            """;

    final int actual = new Calibrator().calibrateFixedDocument(document.lines());
    assertEquals(281, actual);
  }
}