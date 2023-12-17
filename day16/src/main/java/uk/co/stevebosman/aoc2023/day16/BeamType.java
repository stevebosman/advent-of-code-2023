package uk.co.stevebosman.aoc2023.day16;

public enum BeamType {
  LeftToRight('>'), RightToLeft('<'), TopToBottom('v'), BottomToTop('^');

  final char display;

  BeamType(final char display) {
    this.display = display;
  }
}
