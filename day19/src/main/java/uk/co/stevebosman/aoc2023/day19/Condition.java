package uk.co.stevebosman.aoc2023.day19;

import uk.co.stevebosman.aoc2023.day19.Part;

@FunctionalInterface
public interface Condition {
  boolean evaluate(Part part) ;
}
