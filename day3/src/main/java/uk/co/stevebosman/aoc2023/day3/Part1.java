package uk.co.stevebosman.aoc2023.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

public class Part1 {
  public static void main(final String[] args) throws IOException {
    final var start = Instant.now();
    try {
      final String input = Files.readString(Path.of(args[0]));
      System.out.println(new EngineInspector(input).inspect());
    } finally {
      final var end = Instant.now();
      System.out.println(Duration.between(start, end));
    }
  }
}
