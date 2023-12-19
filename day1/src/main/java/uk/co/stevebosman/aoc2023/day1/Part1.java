package uk.co.stevebosman.aoc2023.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

public class Part1 {
  public static void main(final String[] args) throws IOException {
    final var start = Instant.now();
    try {
      System.out.println(new Calibrator().calibrateDocument(Files.lines(Path.of(args[0]))));
    } finally {
      final var end = Instant.now();
      System.out.println(Duration.between(start, end));
    }
  }
}
