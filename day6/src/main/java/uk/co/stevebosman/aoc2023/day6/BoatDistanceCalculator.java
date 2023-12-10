package uk.co.stevebosman.aoc2023.day6;

import java.util.List;
import java.util.stream.LongStream;

public class BoatDistanceCalculator {
  public long distanceTravelled(final long speed, final long totalTimeAvailable) {
    return (totalTimeAvailable - speed) * speed;
  }

  public long potentialVictoriesCount(final Race race) {
    return LongStream.range(1, race.totalTimeAvailable() - 1)
                     .mapToObj(i -> distanceTravelled(i, race.totalTimeAvailable()) > race.distance())
                     .filter(b -> b)
                     .count();
  }

  public long victoryFactor(final String input) {
    final List<Race> races = Race.of(input);
    return races.isEmpty()
           ? 0
           : races.stream().map(this::potentialVictoriesCount).reduce(1L, (a, b) -> a * b);
  }
}
