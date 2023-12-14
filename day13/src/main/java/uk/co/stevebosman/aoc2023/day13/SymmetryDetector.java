package uk.co.stevebosman.aoc2023.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

  public int processSmudged() {
    return lines.keySet().stream().map(this::processSmudged).reduce(Integer::sum).orElse(0);
  }

  public int processSmudged(final int index) {
    final List<String> lines = this.lines.get(index);

    final int originalSymmetry = calculateSymmetry(lines);

    final int rowLength = lines.get(0).length();
    for (int i = 0; i < lines.size(); i++) {
      for (int j = 0; j < rowLength; j++) {
        final String line = lines.get(i);
        final List<String> newLines = new ArrayList<>(lines);
        newLines.set(i, buildNewLine(line, j));
        final int symmetry = calculateSymmetry(newLines, originalSymmetry);
        if (symmetry > -1 && symmetry != originalSymmetry) {
          return symmetry;
        }
      }
    }
    throw new IllegalStateException("Should not get here (" + index + "/" + this.lines.size() + "):\n" + lines.stream().map(s->s +"\n").collect(Collectors.joining()) + "---" + index);
  }

  private static String buildNewLine(final String line, final int swapCharAt) {
    final char c = line.charAt(swapCharAt);
    return new StringBuilder(line).replace(swapCharAt, swapCharAt + 1, c == '#'
                                                     ? "."
                                                     : "#")
                                  .toString();
  }

  public int process(final int index) {
    final List<String> lines = this.lines.get(index);
    return calculateSymmetry(lines);
  }

  private int calculateSymmetry(final List<String> lines) {
    return calculateSymmetry(lines, -1);
  }

  private int calculateSymmetry(final List<String> lines, final int ignoreResult) {
    List<Integer> hashes = buildRowHashes(lines);
    int symmetry = 100 * testSymmetry(hashes, ignoreResult%100 == 0 ? ignoreResult/100 -1 : -1);

    if (symmetry < 0 || symmetry == ignoreResult) {
      symmetry = testSymmetry(buildColumnHashes(lines), ignoreResult-1);
    }

    return symmetry == ignoreResult ? -1 : symmetry;
  }

  private static List<Integer> buildRowHashes(final List<String> lines) {
    final List<Integer> rowHashes = new ArrayList<>();
    for (final String line: lines) {
      rowHashes.add(line.hashCode());
    }
    return rowHashes;
  }

  private List<Integer> buildColumnHashes(final List<String> lines) {
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
    return columnHashes;
  }

  private int testSymmetry(final List<Integer> hashes, final int ignore) {
    if (new HashSet<>(hashes).size() == hashes.size()) return -1;

    for (int i = 0; i < hashes.size() - 1; i++) {
      if (i==ignore) continue;
      if (hashes.get(i).equals(hashes.get(i + 1)) && isPerfectSymmetry(hashes, i)) {
        return i + 1;
      }
    }
    return -1;
  }

  private boolean isPerfectSymmetry(final List<Integer> hashes, int i) {
    int j = i + 1;
    final int jMax = hashes.size();
    while( i >= 0 && j < jMax && hashes.get(i).equals(hashes.get(j))) {
      i--;
      j++;
    }
    return i == -1 || j == jMax;
  }
}
