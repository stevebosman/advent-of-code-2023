package uk.co.stevebosman.aoc2023.day19;

import java.util.Arrays;
import java.util.Optional;

public enum Category {
  ExtremelyCoolLooking("x"), Musical("m"), Aerodynamic("a"), Shiny("s");
  private final String code;
  Category(final String code) {
    this.code = code;
  }

  public static Category of(final String code) {
    return Arrays.stream(values())
                 .filter(category-> code.equals(category.code))
                 .findFirst().orElseThrow(() -> new IllegalArgumentException("Unknown code: " + code));
  }
}
