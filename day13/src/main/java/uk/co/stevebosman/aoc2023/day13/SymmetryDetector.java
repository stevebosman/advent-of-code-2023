package uk.co.stevebosman.aoc2023.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SymmetryDetector {
  Map<Integer, List<String>> lines = new HashMap<>();
  public SymmetryDetector(final String input) {
    final List<String> lines = input.lines().toList();
    int index = 0;
    List<String> currentMapList = new ArrayList<>();
    for (final String currentLine : lines) {
      if (currentLine.isBlank()) {
        this.lines.put(index++, currentMapList);
        currentMapList = new ArrayList<>();
        continue;
      }
      currentMapList.add(currentLine);
    }
    this.lines.put(index, currentMapList);
  }

  public int size() {
    return lines.size();
  }

  public int processAll() {
    return lines.keySet().parallelStream().map(this::process).reduce(Integer::sum).orElse(0);
  }

  public int process(final int index) {
    final List<Integer> rowHashes = new ArrayList<>();
    final List<String> lines = this.lines.get(index);
    for (final String line: lines) {
      rowHashes.add(line.hashCode());
    }
    int symmetry = 100 * testSymmetry(rowHashes);

    if (symmetry < 0) {
      final int rowLength = lines.get(0)
                                 .length();
      final List<Integer> columnHashes = new ArrayList<>();
      for (int i = 0; i < rowLength; i++) {
        final int column = i;
        columnHashes.add(lines.stream()
                              .map(s -> s.charAt(column))
                              .map(Object::toString)
                              .collect(Collectors.joining())
                              .hashCode());
      }
      symmetry = testSymmetry(columnHashes);
    }

    return symmetry;
  }

  private int testSymmetry(final List<Integer> hashes) {
    if (new HashSet<>(hashes).size() == hashes.size()) return -1;

    for (int i = 0; i < hashes.size() - 1; i++) {
      if (hashes.get(i).equals(hashes.get(i + 1)) && isPerfectSymmetry(hashes, i)) {
        return i + 1;
      }
    }
    return -1;
  }

  private boolean isPerfectSymmetry(final List<Integer> hashes, int i) {
    int j = i + 1;
    int jMax = hashes.size();
    while( i >= 0 && j < jMax && hashes.get(i).equals(hashes.get(j)) ) {
      i--;
      j++;
    }
    return i == -1 || j == jMax;
  }
}
