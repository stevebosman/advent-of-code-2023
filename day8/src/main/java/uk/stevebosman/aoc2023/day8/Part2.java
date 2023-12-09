package uk.stevebosman.aoc2023.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class Part2 {
  public static void main(final String[] args) throws IOException {
    final String input = Files.readString(Path.of(args[0]));
    System.out.println(LocalDateTime.now() + ": " + new AdventMap(input).ghostCount());
  }
}
