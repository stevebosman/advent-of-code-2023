package uk.stevebosman.aoc2023.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Part1 {
  public static void main(final String[] args) throws IOException {
    try (final Stream<String> lines = Files.lines(Path.of(args[0]))) {
      System.out.println(lines.map(c -> Card.of(c)
                                            .score())
                              .reduce(Integer::sum));
    }
  }
}
