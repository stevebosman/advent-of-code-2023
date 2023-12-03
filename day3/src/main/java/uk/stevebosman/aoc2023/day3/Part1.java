package uk.stevebosman.aoc2023.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Part1 {
  public static void main(final String[] args) throws IOException {
    final EngineInspector inspector = new EngineInspector(Files.readString(Path.of(args[0])));
    System.out.println(inspector.inspect());
  }
}
