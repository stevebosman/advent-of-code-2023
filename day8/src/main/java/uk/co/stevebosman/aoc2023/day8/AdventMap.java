package uk.co.stevebosman.aoc2023.day8;

import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdventMap {
  private final Directions directions;
  private final Map<String, Location> locations;

  public AdventMap(final String input) {
    this.directions = new Directions(input.lines()
                                          .findFirst()
                                          .orElseThrow());
    this.locations = input.replaceAll(" ", "")
                          .lines()
                          .filter(s -> s.matches("[a-zA-Z0-9]{3}=\\([a-zA-Z0-9]{3},[a-zA-Z0-9]{3}\\)"))
                          .collect(Collectors.toMap(
                                  s -> s.substring(0, 3),
                                  s -> new Location(s.substring(5, 8), s.substring(9, 12))
                          ));
  }

  public long count() {
    return count("AAA", "ZZZ");
  }
  public long count(final String from, final String toPattern) {
    int count = 0;
    String currentLocation = from;
    while (!currentLocation.matches(toPattern)) {
      final Directions.Direction direction = directions.next();
      currentLocation = getNewLocation(currentLocation, direction);
      count++;
    }
    return count;
  }

  private String getNewLocation(final String currentLocation, final Directions.Direction direction) {
    final String newLocation;
    if (direction == Directions.Direction.Left) {
      newLocation = locations.get(currentLocation)
                                 .left();
    } else {
      newLocation = locations.get(currentLocation)
                                 .right();
    }
    return newLocation;
  }

  public long ghostCount() {
    final List<Long> results = locations.keySet()
                               .stream()
                               .filter(s -> s.endsWith("A"))
                               .map(s -> count(s, "..Z"))
                               .toList();
    long result = ArithmeticUtils.lcm(results.get(0), results.get(1));
    for (int i = 2; i < results.size(); i++) {
      result = ArithmeticUtils.lcm(result, results.get(i));
    }

    return result;
  }
}
