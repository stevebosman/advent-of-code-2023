package uk.stevebosman.aoc2023.day7.part1;

import java.util.HashMap;
import java.util.Map;

public enum HandType {
  High,
  OnePair,
  TwoPair,
  ThreeOfAKind,
  FullHouse,
  FourOfAKind,
  FiveOfAKind;

  static HandType of(final String cards) {
    final Map<Character, Integer> counts = new HashMap<>();
    for (int i = 0; i < cards.length(); i++) {
      final char c = cards.charAt(i);
      counts.put(c, counts.getOrDefault(c, 0) + 1);
    }
    final int max = counts.values().stream().max(Integer::compareTo).orElse(0);

    if (counts.size() == 5) {
      return High;
    } else if (counts.size() == 4) {
      return OnePair;
    } else if (counts.size() == 3) {
      return max == 2 ? TwoPair : ThreeOfAKind;
    } else if (counts.size() == 2) {
      return max == 3 ? FullHouse : FourOfAKind;
    } else if (counts.size() == 1) {
      return FiveOfAKind;
    } else {
      return null;
    }
  }
}
