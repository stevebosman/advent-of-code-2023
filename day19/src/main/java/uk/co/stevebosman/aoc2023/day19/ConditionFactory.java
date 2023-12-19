package uk.co.stevebosman.aoc2023.day19;

public class ConditionFactory {
  public static Condition newCondition(final Category category, final String conditionType, final int quantity) {
    return switch (category) {
      case Category.ExtremelyCoolLooking -> getExtremelyCoolLookingCondition(conditionType, quantity);
      case Category.Musical -> getMusicalCondition(conditionType, quantity);
      case Category.Aerodynamic -> getAerodynamicCondition(conditionType, quantity);
      case Category.Shiny -> getShinyCondition(conditionType, quantity);
    };
  }

  private static Condition getExtremelyCoolLookingCondition(final String conditionType, final int quantity) {
    if ("<".equals(conditionType)) {
      return part -> part.x() < quantity;
    }
    return part -> part.x() > quantity;
  }

  private static Condition getMusicalCondition(final String conditionType, final int quantity) {
    if ("<".equals(conditionType)) {
      return part -> part.m() < quantity;
    }
    return part -> part.m() > quantity;
  }

  private static Condition getAerodynamicCondition(final String conditionType, final int quantity) {
    if ("<".equals(conditionType)) {
      return part -> part.a() < quantity;
    }
    return part -> part.a() > quantity;
  }

  private static Condition getShinyCondition(final String conditionType, final int quantity) {
    if ("<".equals(conditionType)) {
      return part -> part.s() < quantity;
    }
    return part -> part.s() > quantity;
  }
}
