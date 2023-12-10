package uk.co.stevebosman.aoc2023.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdventMapTest {
  @Test
  public void map1() {
    final String input = """
            R L
                        
            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG,GGG)
            ZZZ = (ZZZ, ZZZ)""";
    final AdventMap instance = new AdventMap(input);
    assertNotNull(instance);
    assertEquals(2, instance.count());
  }

  @Test
  public void map2() {
    final String input = """
            LLR
                        
            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)""";
    final AdventMap instance = new AdventMap(input);
    assertNotNull(instance);
    assertEquals(6, instance.count());
  }

  @Test
  public void map3() {
    final String input = """
            LR
                        
            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)""";
    final AdventMap instance = new AdventMap(input);
    assertNotNull(instance);
    assertEquals(6, instance.ghostCount());
  }
}