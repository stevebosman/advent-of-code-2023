package uk.co.stevebosman.aoc2023.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AlmanacTest {
  @Test
  void testCanLoadAlmanac() {
    // Given
    final String input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4""";
    // When
    final Almanac instance = Almanac.of(input.lines(), false);
    // Then
    assertEquals(4, instance.seeds()
                            .size(), "Unexpected seed ranges " + instance.seeds());
    assertAll(
            () -> assertNotNull(instance.seedToSoil()),
            () -> assertNotNull(instance.soilToFertilizer()),
            () -> assertNotNull(instance.fertilizerToWater()),
            () -> assertNotNull(instance.waterToLight()),
            () -> assertNotNull(instance.lightToTemperature()),
            () -> assertNotNull(instance.temperatureToHumidity()),
            () -> assertNotNull(instance.humidityToLocation())
    );
    assertEquals(50L, instance.seedToSoil()
                              .get(98L));
    // chain gives expected result
    assertEquals(81L, instance.seedToSoil()
                              .get(79L));
    assertEquals(81L, instance.soilToFertilizer()
                              .get(81L));
    assertEquals(81L, instance.fertilizerToWater()
                              .get(81L));
    assertEquals(74L, instance.waterToLight()
                              .get(81L));
    assertEquals(78L, instance.lightToTemperature()
                              .get(74L));
    assertEquals(78L, instance.temperatureToHumidity()
                              .get(78L));
    assertEquals(82L, instance.humidityToLocation()
                              .get(78L));
    //
    assertEquals(35L, instance.findMinimumLocation());
  }

  @Test
  void testCanLoadAlmanacRangedSeeds() {
    // Given
    final String input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4""";
    // When
    final Almanac instance = Almanac.of(input.lines(), true);
    // Then
    assertEquals(2, instance.seeds()
                            .size(), "Unexpected seed ranges " + instance.seeds());
    assertAll(
            () -> assertNotNull(instance.seedToSoil()),
            () -> assertNotNull(instance.soilToFertilizer()),
            () -> assertNotNull(instance.fertilizerToWater()),
            () -> assertNotNull(instance.waterToLight()),
            () -> assertNotNull(instance.lightToTemperature()),
            () -> assertNotNull(instance.temperatureToHumidity()),
            () -> assertNotNull(instance.humidityToLocation())
    );
    assertEquals(50L, instance.seedToSoil()
                              .get(98L));
    // chain gives expected result
    assertEquals(81L, instance.seedToSoil()
                              .get(79L));
    assertEquals(81L, instance.soilToFertilizer()
                              .get(81L));
    assertEquals(81L, instance.fertilizerToWater()
                              .get(81L));
    assertEquals(74L, instance.waterToLight()
                              .get(81L));
    assertEquals(78L, instance.lightToTemperature()
                              .get(74L));
    assertEquals(78L, instance.temperatureToHumidity()
                              .get(78L));
    assertEquals(82L, instance.humidityToLocation()
                              .get(78L));
    //
    assertEquals(46L, instance.findMinimumLocation());
  }
}