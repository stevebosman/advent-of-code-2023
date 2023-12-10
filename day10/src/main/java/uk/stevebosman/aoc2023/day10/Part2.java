package uk.stevebosman.aoc2023.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

public class Part2 {
  public static void main(final String[] args) throws IOException {
    final Instant start = Instant.now();
    try {
      final String input = Files.readString(Path.of(args[0]));
      System.out.println(new PipeMap(input).countInside());
    } finally {
      final Instant end = Instant.now();
      System.out.println(Duration.between(start, end));
    }
  }
}
