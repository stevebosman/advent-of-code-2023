package uk.co.stevebosman.aoc2023.day1;

import java.util.List;
import java.util.stream.Stream;

public class Calibrator {
  private static final List<String> NUMBER_TEXT = List.of(
          "one", "two", "three", "four",
          "five", "six", "seven", "eight", "nine"
  );

  public int calibrateDocument(final Stream<String> lines) {
    return lines.map(this::calibrateLine).reduce(0, Integer::sum);
  }

  public int calibrateLine(final String line) {
    final int[] digits = line.codePoints()
                             .filter(codePoint -> codePoint >= '0' && codePoint <= '9')
                             .map(c -> c - '0')
                             .toArray();
    return digits[0] * 10 + digits[digits.length - 1];
  }

  public String fixLine(final String line) {
    final StringBuilder sb = new StringBuilder(line + " ".repeat(4));
    for (int i = 0; i < sb.length() - 6; i++) {
      for (int j = 0; j < NUMBER_TEXT.size(); j++) {
        final String numberText = NUMBER_TEXT.get(j);
        final int numberTextLength = numberText.length();
        if (numberText.equals(sb.substring(i, i + numberTextLength))) {
          sb.replace(i, i + 1, Integer.toString(j + 1));
        }
      }
    }
    return sb.toString().trim();
  }

  public int calibrateFixedDocument(final Stream<String> lines) {
    return lines.map(l -> calibrateLine(fixLine(l))).reduce(0, Integer::sum);
  }
}