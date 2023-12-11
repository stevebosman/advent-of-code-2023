package uk.co.stevebosman.aoc2023.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GalaxyMap {
  private List<Galaxy> galaxies;

  public GalaxyMap(final List<String> originalLines, final int expansion) {
    // find blank lines
    final Map<Integer, Integer> rowIndexMap = new HashMap<>();
    int trueRow = 0;
    for (int rowIndex = 0; rowIndex < originalLines.size(); rowIndex++) {
      final String line = originalLines.get(rowIndex);
      if (line.contains("#")) {
        rowIndexMap.put(rowIndex, trueRow);
        trueRow++;
      } else {
        trueRow += expansion;
      }
    }

    // find blank columns
    final int lineLength = originalLines.get(0)
                                        .length();
    final Map<Integer, Integer> columnIndexMap = new HashMap<>();
    int trueColumn = 0;
    for (int columnIndex = 0; columnIndex < lineLength; columnIndex++) {
      final int fixedColumnIndex = columnIndex;
      final boolean emptyColumn = originalLines.stream()
                                               .map(s -> s.charAt(fixedColumnIndex))
                                               .allMatch(c -> c == '.');
      if (emptyColumn) {
        trueColumn += expansion;
      } else {
        columnIndexMap.put(columnIndex, trueColumn);
        trueColumn++;
      }
    }

    // build up list
    final List<Galaxy> galaxies = new ArrayList<>();
    for (int i = 0; i < originalLines.size(); i++) {
      final String line = originalLines.get(i);
      for (int j = 0; j < lineLength; j++) {
        if (line.charAt(j) == '#') {
          galaxies.add(new Galaxy(columnIndexMap.get(j), rowIndexMap.get(i)));
        }
      }
    }
    this.galaxies = Collections.unmodifiableList(galaxies);
  }

  public List<Galaxy> getGalaxies() {
    return galaxies;
  }

  public long sumDistances() {
    long sum = 0;
    for (int i = 0; i < galaxies.size(); i++) {
      final Galaxy g = galaxies.get(i);
      for (int j = i + 1; j < galaxies.size(); j++) {
        sum += g.manhattanDistance(galaxies.get(j));
      }
    }
    return sum;
  }
}
