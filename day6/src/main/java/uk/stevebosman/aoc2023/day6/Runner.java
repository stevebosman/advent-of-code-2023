package uk.stevebosman.aoc2023.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Runner {
  public static void main(final String[] args) throws IOException {
    final String input = Files.readString(Path.of(args[0]));
    System.out.println(LocalDateTime.now() + ": " + new BoatDistanceCalculator().victoryFactor(input));
  }
}
