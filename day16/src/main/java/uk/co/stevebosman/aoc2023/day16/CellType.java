package uk.co.stevebosman.aoc2023.day16;

import java.util.Set;

public enum CellType {
  Empty('.'),
  ForwardMirror('/',
          new NextBeam(BeamType.LeftToRight, Set.of(BeamType.BottomToTop)),
          new NextBeam(BeamType.RightToLeft, Set.of(BeamType.TopToBottom)),
          new NextBeam(BeamType.BottomToTop, Set.of(BeamType.LeftToRight)),
          new NextBeam(BeamType.TopToBottom, Set.of(BeamType.RightToLeft))
  ),
  BackMirror('\\',
          new NextBeam(BeamType.LeftToRight, Set.of(BeamType.TopToBottom)),
          new NextBeam(BeamType.RightToLeft, Set.of(BeamType.BottomToTop)),
          new NextBeam(BeamType.BottomToTop, Set.of(BeamType.RightToLeft)),
          new NextBeam(BeamType.TopToBottom, Set.of(BeamType.LeftToRight))
  ),
  VerticalMirror('|',
          new NextBeam(BeamType.LeftToRight, Set.of(BeamType.TopToBottom, BeamType.BottomToTop)),
          new NextBeam(BeamType.RightToLeft, Set.of(BeamType.TopToBottom, BeamType.BottomToTop)),
          new NextBeam(BeamType.BottomToTop, Set.of()),
          new NextBeam(BeamType.TopToBottom, Set.of())
  ),
  HorizontalMirror('-',
          new NextBeam(BeamType.LeftToRight, Set.of()),
          new NextBeam(BeamType.RightToLeft, Set.of()),
          new NextBeam(BeamType.BottomToTop, Set.of(BeamType.LeftToRight, BeamType.RightToLeft)),
          new NextBeam(BeamType.TopToBottom, Set.of(BeamType.LeftToRight, BeamType.RightToLeft))
  );

  final char display;
  private final NextBeam[] beams;

  CellType(final char display, final NextBeam... beams) {
    this.display = display;
    this.beams = beams;
  }

  public Set<BeamType> nextBeamTypes(final BeamType cellType) {
    if (this == Empty) return Set.of();
    for (final NextBeam type: beams) {
      if (type.in == cellType) return type.out;
    }
    throw new IllegalArgumentException(this + ": Cannot determine for " + cellType);
  }

  public static CellType of(final char value) {
    return switch(value) {
      case '.' -> Empty;
      case '/' -> ForwardMirror;
      case '\\' -> BackMirror;
      case '|' -> VerticalMirror;
      case '-' -> HorizontalMirror;
      default -> throw new IllegalArgumentException("Unrecognised cell type: " + value);
    };
  }

  private record NextBeam(BeamType in, Set<BeamType> out) {

  }
}

