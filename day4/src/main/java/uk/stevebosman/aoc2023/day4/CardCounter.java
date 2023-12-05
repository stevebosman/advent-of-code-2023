package uk.stevebosman.aoc2023.day4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CardCounter {
  int count(final Stream<String> lines) {
    final List<Card> cards = lines.map(Card::of).toList();
    System.out.println(cards);
    final int[] counts = new int[cards.size()];
    for (int i = 0; i < cards.size(); i++) {
      final int matchCount = cards.get(i)
                                  .matchCount();
      counts[i] += 1;
      if (matchCount > 0) {
        System.out.print("card " + (i + 1) + " wins " + counts[i] + " of");
        for (int j = i+1; j < i+matchCount+1; j++) {
          System.out.print(" " + (j + 1));
          if (j < cards.size()) {
            counts[j] += counts[i];
          }
        }
        System.out.println(" -> " + Arrays.toString(counts));
      }
    }

    int total = 0;
    for (final int count : counts) {
      total += count;
    }
    return total;
  }

}
