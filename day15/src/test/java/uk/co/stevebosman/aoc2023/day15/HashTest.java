package uk.co.stevebosman.aoc2023.day15;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class HashTest {
  @ParameterizedTest
  @CsvSource(value = {
    "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7|1320"
  }, delimiter = '|')
  void hashSequence(String input, int expected) {
    assertEquals(expected, Hash.hashSequence(input));
  }

  @ParameterizedTest
  @CsvSource(value = {
          "rn=1,30",
          "cm-,253",
          "qp=3,97",
          "cm=2,47",
          "qp-,14",
          "pc=4,180",
          "ot=9,9",
          "ab=5,197",
          "pc-,48",
          "pc=6,214",
          "ot=7,231"
  })
  void hash(String input, int expected) {
    assertEquals(expected, Hash.hash(input));
  }
}