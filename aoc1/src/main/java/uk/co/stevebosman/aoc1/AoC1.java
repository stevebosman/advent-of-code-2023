package uk.co.stevebosman.aoc1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class AoC1 {
  public static void main(final String[] args) throws IOException {
    System.out.println(new AoC1().calibrateDocument(Files.lines(Path.of(args[0]))));
  }

  public int calibrateDocument(final Stream<String> lines) {
    return lines.map(this::calibrateLine).reduce(0, Integer::sum);
  }

  public int calibrateLine(final String line) {
    final int[] digits = line.codePoints()
                             .filter(codePoint -> codePoint >= '0' && codePoint <= '9')
                             .map(c -> c - '0')
                             .toArray();
    return digits[0]*10 + digits[digits.length-1];
  }
}