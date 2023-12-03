package uk.stevebosman.aoc2023.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Part1 {
  public static void main(final String[] args) throws IOException {
    System.out.println(new GameVerifier().verifyGames(Files.lines(Path.of(args[0]))));
  }
}
