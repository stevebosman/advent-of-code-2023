package uk.co.stevebosman.aoc2023.day7.part2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTest {
  @ParameterizedTest
  @CsvSource(value = {
          "32T3K 765, OnePair, 765",
          "T55J5 684, FourOfAKind, 684",
          "KK677 28, TwoPair, 28",
          "KTJJT 220, FourOfAKind, 220",
          "QQQJA 483, FourOfAKind, 483",
          "77777 123, FiveOfAKind, 123",
          "QQQQA 99, FourOfAKind, 99",
          "QQQJJ 12, FiveOfAKind, 12",
          "459T2 5, High, 5",
  })
  void createHand(final String cards, final HandType handType, final int bid) {
    // When
    final Hand hand = Hand.of(cards);
    // Then
    assertAll(
            ()->assertEquals(5, hand.cards().size()),
            ()->assertEquals(bid, hand.bid()),
            ()->assertEquals(handType, hand.handType())
    );
  }


  @ParameterizedTest
  @CsvSource(value = {
          "77777, QQQQA",
          "QQQJJ, QQQQA",
          "QQQJJ, T55J5",
          "T55J5, 666A2",
          "KK677, 32T3K",
          "32T3K, 459T2",
          "33332, 2AAAA",
          "77888, 77788",
          "QQQJA, T55J5",
          "2222A, JKKK2",
          "KKKKK, JJJJJ"
  })
  void compare(final String cards1, final String cards2) {
    // When
    final Hand hand1 = Hand.of(cards1);
    final Hand hand2 = Hand.of(cards2);
    // Then
    assertAll(
            () -> assertTrue(hand1.compareTo(hand2) > 0, "hand1 should be after hand2"),
            () -> assertTrue(hand2.compareTo(hand1) < 0, "hand2 should be before hand1"),
            () -> assertEquals(0, Hand.of(cards1).compareTo(hand1), "identical hands should be the same"),
            () -> assertEquals(0, Hand.of(cards2).compareTo(hand2), "identical hands should be the same")
    );
  }

}
