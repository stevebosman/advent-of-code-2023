package uk.co.stevebosman.aoc2023.day19;

import static uk.co.stevebosman.aoc2023.day19.IntRange.MAX;

public record Part2Condition(IntRange x, IntRange m, IntRange a, IntRange s) {
  public static Part2Condition of(final Category category, final String conditionType, final int quantity) {
    return switch (category) {
      case ExtremelyCoolLooking -> new Part2Condition(newRange(conditionType, quantity), MAX, MAX, MAX);
      case Musical -> new Part2Condition(MAX, newRange(conditionType, quantity), MAX, MAX);
      case Aerodynamic -> new Part2Condition(MAX, MAX, newRange(conditionType, quantity), MAX);
      case Shiny -> new Part2Condition(MAX, MAX, MAX, newRange(conditionType, quantity));
    };
  }

  private static IntRange newRange(final String conditionType, final int quantity) {
    return "<".equals(conditionType)
           ? new IntRange(1, quantity - 1)
           : new IntRange(quantity + 1, 4000);
  }
}
