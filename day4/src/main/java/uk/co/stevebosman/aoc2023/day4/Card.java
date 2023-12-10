package uk.co.stevebosman.aoc2023.day4;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public record Card(int id, Set<Integer> winners, Set<Integer> have, int matchCount, int score) {
  public static Card of(final String line) {
    final String[] split1 = line.split(":");
    final int id = Integer.parseInt(split1[0].substring(4).trim());
    final String[] winnersHave = split1[1].split("\\|");
    final Set<Integer> winners = toIntegerSet(winnersHave[0].split(" "));
    final Set<Integer> have = toIntegerSet(winnersHave[1].split(" "));

    final int matchCount = winners.stream()
                            .filter(have::contains)
                            .toList()
                            .size();

    return new Card(id, winners, have, matchCount, matchCount == 0
                                       ? 0
                                       : 1 << matchCount - 1);
  }

  private static Set<Integer> toIntegerSet(final String[] array) {
    return Arrays.stream(array)
                 .filter(v -> v.length() > 0)
                 .map(Integer::valueOf)
                 .collect(Collectors.toSet());
  }
}
