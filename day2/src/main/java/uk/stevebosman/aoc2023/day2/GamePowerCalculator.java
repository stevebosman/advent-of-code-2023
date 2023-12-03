package uk.stevebosman.aoc2023.day2;

import java.util.stream.Stream;

public class GamePowerCalculator {
  int totalPower(final Stream<String> lines) {
    return lines.map(this::gamePower)
                .reduce(0, Integer::sum);
  }

  int gamePower(final String game) {
    final String[] parts = game.split(":");
    final String[] rounds = parts[1].split(";");

    int maxRed = 0;
    int maxGreen = 0;
    int maxBlue = 0;

    for (final String round : rounds) {
      final CubeCounts counts = countColors(round);
      maxRed = Math.max(maxRed, counts.red());
      maxGreen = Math.max(maxGreen, counts.green());
      maxBlue = Math.max(maxBlue, counts.blue());
    }
    return maxRed * maxGreen * maxBlue;
  }

  CubeCounts countColors(final String round) {
    final String[] colors = round.split(",");

    int red = 0;
    int green = 0;
    int blue = 0;

    for (final String color: colors) {
      final String[] colorParts = color.trim().split(" ");
      final String colorName = colorParts[1];
      final int colorCount = Integer.parseInt(colorParts[0]);
      if ("red".equals(colorName)) {
        red = colorCount;
      } else if ("green".equals(colorName)) {
        green = colorCount;
      } else if ("blue".equals(colorName)) {
        blue = colorCount;
      }
    }
    return new CubeCounts(red, green, blue);
  }
}
