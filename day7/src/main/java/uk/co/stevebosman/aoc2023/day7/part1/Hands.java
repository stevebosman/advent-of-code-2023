package uk.co.stevebosman.aoc2023.day7.part1;

import java.util.List;
import java.util.stream.Stream;

public record Hands(List<Hand> hands) {
  public int score() {
    final List<Hand> sorted = hands.stream().sorted().toList();
    int score = 0;
    for (int i = 0; i < sorted.size(); i++) {
      score += (i+1) * sorted.get(i).bid() ;
    }
    return score;
  }

  public static Hands of(final Stream<String> lines) {
    return new Hands(lines.map(Hand::of).toList());
  }
}
