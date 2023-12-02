package uk.co.stevebosman.aoc1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Part1 {
  public static void main(final String[] args) throws IOException {
    System.out.println(new AoC1().calibrateDocument(Files.lines(Path.of(args[0]))));
  }
}
