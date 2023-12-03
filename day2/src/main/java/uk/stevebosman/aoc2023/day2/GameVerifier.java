package uk.stevebosman.aoc2023.day2;

import java.util.stream.Stream;

public class GameVerifier {
  int verifyGames(final Stream<String> lines) {
    return lines.map(l -> verifyGame(l, 12, 13, 14))
                .reduce(0, Integer::sum);
  }

  int verifyGame(final String game, final int red, final int green, final int blue) {
    final String[] parts = game.split(":");
    final int gameNumber = Integer.parseInt(parts[0].substring(5));
    final String[] rounds = parts[1].split(";");
    boolean allGood = true;
    for (final String round : rounds) {
      if (!verifyRound(round.trim(), red, green, blue)) {
        allGood = false;
        break;
      }
    }
    return allGood
           ? gameNumber
           : 0;
  }

  boolean verifyRound(final String round, final int red, final int green, final int blue) {
    final String[] colors = round.split(",");
    boolean result = true;
    for (final String color: colors) {
      final String[] colorParts = color.trim().split(" ");
      final String colorName = colorParts[1];
      final int colorCount = Integer.parseInt(colorParts[0]);
      if ("red".equals(colorName)) {
        result = colorCount <= red;
      } else if ("green".equals(colorName)) {
        result = colorCount <= green;
      } else if ("blue".equals(colorName)) {
        result = colorCount <= blue;
      }
      if (!result) {
        break;
      }
    }
    return result;
  }
}
