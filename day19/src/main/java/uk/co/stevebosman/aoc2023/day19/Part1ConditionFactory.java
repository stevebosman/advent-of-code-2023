package uk.co.stevebosman.aoc2023.day19;

public class Part1ConditionFactory {
  public static Part1Condition newPart1Condition(final Category category, final String conditionType, final int quantity) {
    return switch (category) {
      case Category.ExtremelyCoolLooking -> getExtremelyCoolLookingCondition(conditionType, quantity);
      case Category.Musical -> getMusicalCondition(conditionType, quantity);
      case Category.Aerodynamic -> getAerodynamicCondition(conditionType, quantity);
      case Category.Shiny -> getShinyCondition(conditionType, quantity);
    };
  }

  private static Part1Condition getExtremelyCoolLookingCondition(final String conditionType, final int quantity) {
    if ("<".equals(conditionType)) {
      return part -> part.x() < quantity;
    }
    return part -> part.x() > quantity;
  }

  private static Part1Condition getMusicalCondition(final String conditionType, final int quantity) {
    if ("<".equals(conditionType)) {
      return part -> part.m() < quantity;
    }
    return part -> part.m() > quantity;
  }

  private static Part1Condition getAerodynamicCondition(final String conditionType, final int quantity) {
    if ("<".equals(conditionType)) {
      return part -> part.a() < quantity;
    }
    return part -> part.a() > quantity;
  }

  private static Part1Condition getShinyCondition(final String conditionType, final int quantity) {
    if ("<".equals(conditionType)) {
      return part -> part.s() < quantity;
    }
    return part -> part.s() > quantity;
  }
}
