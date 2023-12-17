package uk.co.stevebosman.aoc2023.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Cell {
  private final List<BeamType> beams;
  private final CellType type;

  public Cell(final CellType type, final Set<BeamType> beams) {
    this.type = type;
    this.beams = new ArrayList<>(beams);
  }

  public Cell(final CellType type) {
    this.type = type;
    this.beams = List.of();
  }

  public CellType getType() {
    return type;
  }

  public List<BeamType> getBeams() {
    return beams;
  }

  public String toString() {
    if (type != CellType.Empty || beams.isEmpty()) {
      return String.valueOf(type.display);
    } else if (beams.size() == 1) {
      return String.valueOf(beams.get(0).display);
    } else {
      return String.valueOf(beams.size());
    }
  }
}
