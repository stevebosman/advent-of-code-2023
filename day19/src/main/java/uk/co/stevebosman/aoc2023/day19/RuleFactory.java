package uk.co.stevebosman.aoc2023.day19;

import java.util.regex.Pattern;

public class RuleFactory {
  private static final Pattern RULE_PATTERN = Pattern.compile("([xmas])([><])(\\d+):([a-zA-Z]+)");

  public static Rule of(final String input) {
    final var matcher = RULE_PATTERN.matcher(input);
    if (matcher.matches()) {
      final var category = Category.of(matcher.group(1));
      final String conditionType = matcher.group(2);
      final int quantity = Integer.parseInt(matcher.group(3));
      final String destination = matcher.group(4);
      final Condition condition = ConditionFactory.newCondition(category, conditionType, quantity);

      return new Rule(condition, destination);
    }
    throw new IllegalArgumentException("Unrecognised rule: " + input);
  }

}
