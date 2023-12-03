package uk.co.stevebosman.aoc2023.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Part2 {
  public static void main(final String[] args) throws IOException {
    System.out.println(new Calibrator().calibrateFixedDocument(Files.lines(Path.of(args[0]))));
  }
}
