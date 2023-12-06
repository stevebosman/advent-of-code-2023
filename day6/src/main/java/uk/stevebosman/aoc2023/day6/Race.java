package uk.stevebosman.aoc2023.day6;

import java.util.ArrayList;
import java.util.List;

public record Race(long totalTimeAvailable, long distance) {
  public static List<Race> of(final String input) {
    final List<Race> result = new ArrayList<>();
    final String[] lines = input.split("\n");
    final String[] times = lines[0].split("\\s+");
    final String[] distances = lines[1].split("\\s+");
    for (int i = 1; i < times.length; i++) {
      result.add(new Race(Long.parseLong(times[i]), Long.parseLong(distances[i])));
    }
    return result;
  }
}
