package uk.co.stevebosman.aoc2023.day15;

import java.util.Arrays;

public class Hash {
  public static int hashSequence(final String input) {
    return Arrays.stream(input.replaceAll("\n", "").split(","))
            .parallel().map(Hash::hash)
            .reduce(Integer::sum)
            .orElse(0);
  }

  public static int hash(final String input) {
    return input.codePoints()
            .reduce(0, (sub, el) -> ((sub + el) * 17) % 256);
  }
}
