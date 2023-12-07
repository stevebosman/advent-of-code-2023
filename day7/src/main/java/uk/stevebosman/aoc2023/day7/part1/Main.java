package uk.stevebosman.aoc2023.day7.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class Main {
  public static void main(final String[] args) throws IOException {
    try (final Stream<String> lines = Files.lines(Path.of(args[0]))) {
      System.out.println(LocalDateTime.now() + ": " + Hands.of(lines).score());
    }
  }
}
