package uk.stevebosman.aoc2023.day4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CardCounter {
  int count(final Stream<String> lines) {
    final List<Card> cards = lines.map(Card::of).toList();
    final int[] counts = new int[cards.size()];
    for (int i = 0; i < cards.size(); i++) {
      final int matchCount = cards.get(i)
                                  .matchCount();
      counts[i] += 1;
      if (matchCount > 0) {
        for (int j = i+1; j < i+matchCount+1; j++) {
          if (j < cards.size()) {
            counts[j] += counts[i];
          }
        }
      }
    }

    int total = 0;
    for (final int count : counts) {
      total += count;
    }
    return total;
  }

}
