package uk.stevebosman.aoc2023.day5;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public record Almanac(
        Set<SeedRange> seeds,
        Map<Long, Long> seedToSoil,
        Map<Long, Long> soilToFertilizer,
        Map<Long, Long> fertilizerToWater,
        Map<Long, Long> waterToLight,
        Map<Long, Long> lightToTemperature,
        Map<Long, Long> temperatureToHumidity,
        Map<Long, Long> humidityToLocation
) {
  public static Almanac of(final Stream<String> lines, final boolean rangedSeeds) {
    final Set<SeedRange> seeds = new HashSet<>();
    final LongRangedMap seedToSoil = new LongRangedMap();
    final LongRangedMap soilToFertilizer = new LongRangedMap();
    final LongRangedMap fertilizerToWater = new LongRangedMap();
    final LongRangedMap waterToLight = new LongRangedMap();
    final LongRangedMap lightToTemperature = new LongRangedMap();
    final LongRangedMap temperatureToHumidity = new LongRangedMap();
    final LongRangedMap humidityToLocation = new LongRangedMap();
    final StageHolder stageHolder = new StageHolder();
    stageHolder.stage = Stage.Seeds;
    lines.forEach(line -> {
      if (!line.isBlank()) {
        if (line.startsWith("seeds:")) {
          final List<Long> seedValues = Arrays.stream(line.substring(6)
                                                          .trim()
                                                          .split(" "))
                                              .map(Long::valueOf)
                                              .toList();
          if (rangedSeeds) {
            for (int i = 0; i < seedValues.size(); i += 2) {
              seeds.add(new SeedRange(seedValues.get(i), seedValues.get(i) + seedValues.get(i + 1)));
            }
          } else {
            for (final Long seedValue : seedValues) {
              seeds.add(new SeedRange(seedValue, seedValue + 1));
            }
          }
        } else if (line.startsWith("seed-to-soil map:")) {
          stageHolder.stage = Stage.SeedToSoil;
        } else if (line.startsWith("soil-to-fertilizer map:")) {
          stageHolder.stage = Stage.SoilToFertilizer;
        } else if (line.startsWith("fertilizer-to-water map:")) {
          stageHolder.stage = Stage.FertilizerToWater;
        } else if (line.startsWith("water-to-light map:")) {
          stageHolder.stage = Stage.WaterToLight;
        } else if (line.startsWith("light-to-temperature map:")) {
          stageHolder.stage = Stage.LightToTemperature;
        } else if (line.startsWith("temperature-to-humidity map:")) {
          stageHolder.stage = Stage.TemperatureToHumidity;
        } else if (line.startsWith("humidity-to-location map:")) {
          stageHolder.stage = Stage.HumidityToLocation;
        } else if (stageHolder.stage == Stage.SeedToSoil) {
          seedToSoil.putRange(Range.of(line));
        } else if (stageHolder.stage == Stage.SoilToFertilizer) {
          soilToFertilizer.putRange(Range.of(line));
        } else if (stageHolder.stage == Stage.FertilizerToWater) {
          fertilizerToWater.putRange(Range.of(line));
        } else if (stageHolder.stage == Stage.WaterToLight) {
          waterToLight.putRange(Range.of(line));
        } else if (stageHolder.stage == Stage.LightToTemperature) {
          lightToTemperature.putRange(Range.of(line));
        } else if (stageHolder.stage == Stage.TemperatureToHumidity) {
          temperatureToHumidity.putRange(Range.of(line));
        } else if (stageHolder.stage == Stage.HumidityToLocation) {
          humidityToLocation.putRange(Range.of(line));
        }

      }
    });
    return new Almanac(seeds, seedToSoil, soilToFertilizer, fertilizerToWater, waterToLight,
                       lightToTemperature, temperatureToHumidity, humidityToLocation);
  }

  public long findMinimumLocation() {
    long minimum = Long.MAX_VALUE;
    for (final SeedRange seedRange : seeds) {
      System.out.println(LocalDateTime.now() + ": " + seedRange);
      minimum = Math.min(minimum,
                         LongStream.range(seedRange.startInclusive(), seedRange.endExclusive())
                                   .parallel()
                                   .map(seedToSoil::get)
                                   .map(soilToFertilizer::get)
                                   .map(fertilizerToWater::get)
                                   .map(waterToLight::get)
                                   .map(lightToTemperature::get)
                                   .map(temperatureToHumidity::get)
                                   .map(humidityToLocation::get)
                                   .min()
                                   .orElse(minimum));
    }
    return minimum;
  }
}
