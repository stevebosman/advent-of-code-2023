package uk.stevebosman.aoc2023.day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineInspector {
  final List<String> lines;
  final Map<Location, Character> symbolLocations;

  public EngineInspector(final String document) {
    lines = document.lines()
                    .toList();

    final Map<Location, Character> symbolLocations = new HashMap<>();
    for (int y = 0; y < lines.size(); y++) {
      final String currentLine = lines.get(y);
      for (int x = 0; x < currentLine.length(); x++) {
        final char c = currentLine.charAt(x);
        if (c != '.' && (c < '0' || c > '9')) {
          symbolLocations.put(new Location(x, y), c);
        }
      }
    }
    this.symbolLocations = Collections.unmodifiableMap(symbolLocations);

  }

  public int inspect() {
    int result = 0;
    for (int y = 0; y < lines.size(); y++) {
      final String currentLine = lines.get(y);
      Integer numStart = null;
      Integer numEnd = null;
      for (int x = 0; x < currentLine.length(); x++) {
        final char c = currentLine.charAt(x);
        if (c >= '0' && c <= '9') {
          if (numStart == null) {
            numStart = x;
          }
          if (x == currentLine.length() - 1) {
            numEnd = x + 1;
          }
        } else if (numStart != null) {
          numEnd = x;
        }
        if (numStart != null && numEnd != null) {
          final int potentialPartNumber = Integer.parseInt(currentLine.substring(numStart, numEnd));
          for (int i = numStart - 1; i <= numEnd; i++) {
            if (symbolLocations.containsKey(new Location(i, y - 1))
                    || symbolLocations.containsKey(new Location(i, y))
                    || symbolLocations.containsKey(new Location(i, y + 1))
            ) {
              result += potentialPartNumber;
              break;
            }
          }
          numStart = null;
          numEnd = null;
        }
      }

    }
    return result;
  }

  public int sumGearRatios() {
    final Map<Location, List<Integer>> potentialGears = new HashMap<>();

    for (int y = 0; y < lines.size(); y++) {
      final String currentLine = lines.get(y);
      Integer numStart = null;
      Integer numEnd = null;
      for (int x = 0; x < currentLine.length(); x++) {
        final char c = currentLine.charAt(x);
        if (c >= '0' && c <= '9') {
          if (numStart == null) {
            numStart = x;
          }
          if (x == currentLine.length() - 1) {
            numEnd = x + 1;
          }
        } else if (numStart != null) {
          numEnd = x;
        }
        if (numStart != null && numEnd != null) {
          final int potentialPartNumber = Integer.parseInt(currentLine.substring(numStart, numEnd));
          for (int i = numStart - 1; i <= numEnd; i++) {
            addGearToPotentialGears(potentialGears, potentialPartNumber, new Location(i, y - 1));
            addGearToPotentialGears(potentialGears, potentialPartNumber, new Location(i, y));
            addGearToPotentialGears(potentialGears, potentialPartNumber, new Location(i, y + 1));
          }
          numStart = null;
          numEnd = null;
        }
      }

    }

    return potentialGears.values().stream()
                         .filter(integers -> integers.size() == 2)
                         .map(integers -> integers.get(0) * integers.get(1))
                         .reduce(Integer::sum).orElse(0);

  }

  private void addGearToPotentialGears(
          final Map<Location, List<Integer>> potentialGears,
          final int potentialPartNumber,
          final Location key) {
    if (symbolLocations.getOrDefault(key, '.') == '*') {
      potentialGears.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(potentialPartNumber);
    }
  }
}
