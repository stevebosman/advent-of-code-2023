package uk.co.stevebosman.aoc2023.day7.part2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public enum HandType {
  High,
  OnePair,
  TwoPair,
  ThreeOfAKind,
  FullHouse,
  FourOfAKind,
  FiveOfAKind;

  static HandType of(final List<Card> cards) {
    final Map<Card, Integer> counts = new HashMap<>();
    for (final Card c : cards) {
      counts.put(c, counts.getOrDefault(c, 0) + 1);
    }

    if (counts.containsKey(Card.CJ)) {
      if (counts.size() == 1) {
        return FiveOfAKind;
      }
      final int jCount = counts.get(Card.CJ);
      counts.remove(Card.CJ);
      final int max = counts.values().stream().max(Integer::compareTo).orElse(0);
      final Optional<Card> fcard = counts.entrySet()
                                         .stream()
                                         .filter(e -> e.getValue() == max)
                                         .map(Map.Entry::getKey).sorted().findFirst();
      fcard.ifPresent(card -> counts.put(card, counts.get(card) + jCount));
    }
    final int max = counts.values().stream().max(Integer::compareTo).orElse(0);

    if (counts.size() == 5) {
      return High;
    } else if (counts.size() == 4) {
      return OnePair;
    } else if (counts.size() == 3) {
      return max == 2 ? TwoPair : ThreeOfAKind;
    } else if (counts.size() == 2) {
      return max == 3
             ? FullHouse
             : FourOfAKind;
    } else if (counts.size() == 1) {
      return FiveOfAKind;
    } else {
      return null;
    }
  }
}
