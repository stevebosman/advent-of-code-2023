package uk.stevebosman.aoc2023.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Part2 {
  public static void main(final String[] args) throws IOException {
    try (final Stream<String> lines = Files.lines(Path.of(args[0]))) {
      final int total = new CardCounter().count(lines);
      System.out.println(total);
    }
  }

}
