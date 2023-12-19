package uk.co.stevebosman.aoc2023.day19;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public record Workflow(String name, List<Rule> rules, String defaultDestination) {
  private static final Pattern WORKFLOW_PATTERN = Pattern.compile("([a-zA-Z]+)\\{(.+),([a-zA-Z]+)}");

  public static Workflow of(final String input) {
    final var matcher = WORKFLOW_PATTERN.matcher(input);
    if (matcher.matches()) {
      final List<Rule> rules = Arrays.stream(matcher.group(2)
                                                    .split(","))
                                     .map(RuleFactory::of)
                                     .toList();
      return new Workflow(
              matcher.group(1),
              rules,
              matcher.group(3)
      );
    }
    throw new IllegalArgumentException("Unrecognised workflow: " + input);
  }

  public String process(final Part part) {
    String destination = defaultDestination;
    for (final Rule rule: rules) {
      if (rule.condition().evaluate(part)) {
        destination = rule.destination();
        break;
      }
    }
    return destination;
  }
}
