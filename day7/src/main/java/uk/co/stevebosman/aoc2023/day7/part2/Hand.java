package uk.co.stevebosman.aoc2023.day7.part2;

import java.util.Arrays;
import java.util.List;

public record Hand(List<Card> cards, HandType handType, int bid)
        implements Comparable<Hand> {
  public static Hand of(final String hand) {
    final String[] parts = hand.split(" ");
    final String cardText = parts[0].toUpperCase();

    final List<Card> cards = Arrays.stream(cardText.split(""))
                              .map(c -> Card.valueOf("C" + c))
                              .toList();

    final int bid = parts.length == 1? 0: Integer.parseInt(parts[1]);

    return new Hand(cards, HandType.of(cards), bid);
  }

  @Override
  public int compareTo(final Hand o) {
    int compare = this.handType.compareTo(o.handType);
    if (compare == 0) {
      for (int i = 0; i < 5; i++) {
        compare = this.cards.get(i)
                            .compareTo(o.cards.get(i));
        if (compare != 0) break;
      }
    }
    return compare;
  }
}
