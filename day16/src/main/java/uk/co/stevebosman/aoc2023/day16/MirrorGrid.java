package uk.co.stevebosman.aoc2023.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MirrorGrid {
  private final int height;
  private final int width;
  private final Cell[][] grid;
  private final Set<Beam> activeBeams;

  public MirrorGrid(final List<String> grid) {
    this.height = grid.size();
    this.width = grid.get(0).length();
    this.grid = new Cell[height][width];


    Set<Beam> activeBeams = Set.of();
    for (int row = 0; row < height; row++) {
      final String line = grid.get(row);
      for (int column = 0; column < width; column++) {
        final CellType cellType = CellType.of(line.charAt(column));
        if (row == 0 && column ==0) {
          final Position start = new Position(0, 0);
          if (cellType == CellType.Empty) {
            activeBeams = Set.of(new Beam(start, BeamType.LeftToRight));
          } else {
            activeBeams = cellType.nextBeamTypes(BeamType.LeftToRight)
                                  .stream()
                                  .map(b->new Beam(start, b))
                                  .collect(Collectors.toSet());
          }
          this.grid[row][column] = new Cell(cellType, Set.of(BeamType.LeftToRight));
        } else {
          this.grid[row][column] = new Cell(cellType);
        }
      }
    }
    this.activeBeams = activeBeams;
  }

  public MirrorGrid(
          final Cell[][] grid,
          final Set<Beam> activeBeams) {
    this.grid = grid;
    this.height = grid.length;
    this.width = grid[0].length;
    this.activeBeams = activeBeams;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public Set<BeamType> getBeamTypes(final int row, final int column) {
    return activeBeams.stream().filter(b->b.start().equals(new Position(row, column))).map(Beam::type).collect(Collectors.toSet());
  }

  public boolean isFinal() {
    return activeBeams==null || activeBeams.isEmpty();
  }

  public MirrorGrid iterate() {
    final Map<Position, List<BeamType>> extraBeams = new HashMap<>();
    final Set<Beam> newActiveBeams = new HashSet<>();

    for (final Beam beam: activeBeams) {
      switch (beam.type()) {
        case BottomToTop -> doBottomToTop(extraBeams, newActiveBeams, beam);
        case TopToBottom -> doTopToBottom(extraBeams, newActiveBeams, beam);
        case LeftToRight -> doLeftToRight(extraBeams, newActiveBeams, beam);
        case RightToLeft -> doRightToLeft(extraBeams, newActiveBeams, beam);
      }
    }

    final Cell[][] grid = new Cell[height][width];
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        final Cell cell = getCell(row, column);
        final Set<BeamType> beams = new HashSet<>(cell.getBeams());
        final List<BeamType> beamTypes = extraBeams.get(new Position(row, column));
        if (beamTypes != null) {
          beams.addAll(beamTypes);
        }
        grid[row][column] = new Cell(cell.getType(), beams);
      }
    }

    return new MirrorGrid(grid, newActiveBeams);
  }

  private void doBottomToTop(final Map<Position, List<BeamType>> extraBeams, final Set<Beam> newActiveBeams, final Beam beam) {
    final Position start = beam.start();
    final int column = start.column();
    for (int row = start.row() - 1; row >= 0; row--) {
      if (doCell(extraBeams, newActiveBeams, beam, column, row)) break;
    }
  }

  private void doTopToBottom(final Map<Position, List<BeamType>> extraBeams, final Set<Beam> newActiveBeams, final Beam beam) {
    final Position start = beam.start();
    final int column = start.column();
    for (int row = start.row() + 1; row < height; row++) {
      if (doCell(extraBeams, newActiveBeams, beam, column, row)) break;
    }
  }

  private void doLeftToRight(final Map<Position, List<BeamType>> extraBeams, final Set<Beam> newActiveBeams, final Beam beam) {
    final Position start = beam.start();
    final int row = start.row();
    for (int column = start.column() + 1; column < width; column++) {
      if (doCell(extraBeams, newActiveBeams, beam, column, row)) break;
    }
  }

  private void doRightToLeft(final Map<Position, List<BeamType>> extraBeams, final Set<Beam> newActiveBeams, final Beam beam) {
    final Position start = beam.start();
    final int row = start.row();
    for (int column = start.column() - 1; column >= 0; column--) {
      if (doCell(extraBeams, newActiveBeams, beam, column, row)) break;
    }
  }

  private boolean doCell(final Map<Position, List<BeamType>> extraBeams, final Set<Beam> newActiveBeams, final Beam beam, final int column, final int row) {
    final Position currentPosition = new Position(row, column);
    final Cell cell = getCell(row, column);
    if (cell.getBeams().contains(beam.type())) {
      return true;
    }
    addBeamType(extraBeams, currentPosition, beam);
    final Set<BeamType> beamTypes = new HashSet<>(cell.getType()
                                                      .nextBeamTypes(beam.type()));
    if (!beamTypes.isEmpty()) {
      beamTypes.stream()
               .map(beamType -> new Beam(currentPosition, beamType))
               .forEach(newActiveBeams::add);
      return true;
    }
    return false;
  }

  private static void addBeamType(
          final Map<Position, List<BeamType>> newBeamPositions,
          final Position currentPosition,
          final Beam beam
  ) {
    newBeamPositions.computeIfAbsent(currentPosition, (k)->new ArrayList<>()).add(beam.type());
  }

  private Cell getCell(final int row, final int column) {
    return grid[row][column];
  }

  public int getEnergy() {
    int energy = 0;
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        if (!getCell(row, column).getBeams().isEmpty()) energy++;
      }
    }

    return energy;
  }

  public static MirrorGrid iterateAll(MirrorGrid grid) {
    while (!grid.isFinal()) {
      grid = grid.iterate();
    }
    return grid;
  }

  public String toEnergyString() {
    final StringBuilder sb = new StringBuilder();
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        sb.append(getCell(row,column).getBeams().isEmpty()?".":"#");
      }
      if (row < height - 1) sb.append("\n");
    }
    return sb.toString();
  }

  public String toString() {
    final StringBuilder sb = new StringBuilder();
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        sb.append(getCell(row,column));
      }
      if (row < height - 1) sb.append("\n");
    }
    return sb.toString();
  }
}
