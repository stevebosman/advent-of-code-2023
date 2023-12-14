package uk.co.stevebosman.aoc2023.day14;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParabolicDishMap {
  final Map<Integer, String> northCache = new HashMap<>();
  final Map<Integer, String> southCache = new HashMap<>();
  final Map<Integer, String> eastCache = new HashMap<>();
  final Map<Integer, String> westCache = new HashMap<>();
  private final byte[][] map;
  private final int width;
  private final int height;

  public ParabolicDishMap(final String input) {
    final List<String> basicMap = input.lines()
                    .toList();
    width = basicMap.get(0)
                    .length();
    height = basicMap.size();
    map = new byte[height][];
    for (int i = 0; i < height; i++) {
      map[i] = basicMap.get(i)
                       .getBytes();
    }
  }

  private static void rollStoneInRow(final Deque<Integer> blanks, final byte[] currentRow, final int column) {
    final byte c = currentRow[column];
    if (c == '.') {
      blanks.add(column);
    } else if (c == 'O') {
      if (!blanks.isEmpty()) {
        currentRow[blanks.removeFirst()] = 'O';
        blanks.add(column);
        currentRow[column] = '.';
      }
    } else {
      blanks.clear();
    }
  }

  public int weight() {
    int weight = 0;
    for (int column = 0; column < width; column++) {
      for (int row = 0; row < height; row++) {
        if (map[row][column] == 'O') {
          weight += height - row;
        }
      }
    }
    return weight;
  }

  public int tiltNorthWeight() {
    tiltNorth();
    return weight();
  }

  public String tiltCycle(final long steps) {
    final List<Integer> hashes = new ArrayList<>();
    final Map<Long, String> states = new HashMap<>();
    int index = -1;
    long i = 0;
    for (; i < steps; i++) {
      tiltNorth();
      tiltWest();
      tiltSouth();
      tiltEast();

      // Are we in a loop?
      // deep hash code is less unique than needed so spice it up
      final int hash = Arrays.deepHashCode(map) * 32 + weight();

      System.out.println(i + ": " + weight() + " - " + hash);

      if (hashes.contains(hash)) {
        index = hashes.indexOf(hash);
        break;
      }
      hashes.add(hash);
      states.put(i, toString(map));

    }

    final String result;
    if (index > -1) {
      final long cycle = i - index;
      final long remainder = (steps - i)%cycle;
      result = states.get(index + remainder - 1);
    } else {
      result = toString(map);
    }

    return result;
  }

  public void tiltNorth() {
    final Deque<Integer> blanks = new ArrayDeque<>();
    for (int column = 0; column < width; column++) {
      final int columnHash = Arrays.hashCode(getColumn(column));
      if (northCache.containsKey(columnHash)) {
        setColumn(column, northCache.get(columnHash)
                                    .getBytes());
      } else {
        blanks.clear();
        for (int row = 0; row < height; row++) {
          rollStoneInColumn(blanks, column, row);
        }
        final byte[] currentColumn = getColumn(column);
        northCache.put(Arrays.hashCode(currentColumn), new String(currentColumn));
      }
    }
  }

  public void tiltSouth() {
    final Deque<Integer> blanks = new ArrayDeque<>();
    for (int column = 0; column < width; column++) {
      final int columnHash = Arrays.hashCode(getColumn(column));
      if (southCache.containsKey(columnHash)) {
        setColumn(column, southCache.get(columnHash)
                                    .getBytes());
      } else {
        blanks.clear();
        for (int row = height - 1; row >= 0; row--) {
          rollStoneInColumn(blanks, column, row);
        }
        final byte[] currentColumn = getColumn(column);
        southCache.put(Arrays.hashCode(currentColumn), new String(currentColumn));
      }
    }
  }

  public void tiltEast() {
    final Deque<Integer> blanks = new ArrayDeque<>();
    for (int row = 0; row < height; row++) {
      final byte[] currentRow = map[row];
      final int rowHash = Arrays.hashCode(currentRow);
      if (eastCache.containsKey(rowHash)) {
        map[row] = eastCache.get(rowHash)
                            .getBytes();
      } else {
        blanks.clear();
        for (int column = width - 1; column >= 0; column--) {
          rollStoneInRow(blanks, currentRow, column);
        }
        eastCache.put(rowHash, new String(currentRow));
      }
    }
  }

  public void tiltWest() {
    final Deque<Integer> blanks = new ArrayDeque<>();
    for (int row = 0; row < height; row++) {
      final byte[] currentRow = map[row];
      final int rowHash = Arrays.hashCode(currentRow);
      if (westCache.containsKey(rowHash)) {
        map[row] = westCache.get(rowHash)
                            .getBytes();
      } else {
        blanks.clear();
        for (int column = 0; column < width; column++) {
          rollStoneInRow(blanks, currentRow, column);
        }
        westCache.put(rowHash, new String(currentRow));
      }
    }
  }

  private byte[] getColumn(final int index) {
    final byte[] column = new byte[map[0].length];
    for (int i = 0; i < column.length; i++) {
      column[i] = map[i][index];
    }
    return column;
  }

  private void setColumn(final int index, final byte[] column) {
    for (int i = 0; i < column.length; i++) {
      map[i][index] = column[i];
    }
  }

  private void rollStoneInColumn(final Deque<Integer> blanks, final int column, final int row) {
    final byte c = map[row][column];
    if (c == '.') {
      blanks.add(row);
    } else if (c == 'O') {
      if (!blanks.isEmpty()) {
        map[blanks.removeFirst()][column] = 'O';
        blanks.add(row);
        map[row][column] = '.';
      }
    } else {
      blanks.clear();
    }
  }

  private String toString(final byte[][] map) {
    final List<String> result = new ArrayList<>(height);
    for (int row = 0; row < height; row++) {
      result.add(new String(map[row]));
    }
    return String.join("\n", result);
  }
}
