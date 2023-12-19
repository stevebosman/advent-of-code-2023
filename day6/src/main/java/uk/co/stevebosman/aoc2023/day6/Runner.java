package uk.co.stevebosman.aoc2023.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

public class Runner {
  public static void main(final String[] args) throws IOException {
    final var start = Instant.now();
    try {
      final String input = Files.readString(Path.of(args[0]));
      System.out.println(new BoatDistanceCalculator().victoryFactor(input));
    } finally {
      final var end = Instant.now();
      System.out.println(Duration.between(start, end));
    }
  }
}
