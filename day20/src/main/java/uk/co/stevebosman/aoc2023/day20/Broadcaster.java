package uk.co.stevebosman.aoc2023.day20;

public final class Broadcaster extends Module {
  public Broadcaster() {
    super("broadcaster");
  }

  @Override
  public void receivePulse(final Module sender, final Pulse pulse) {
    broadcast(pulse);
  }

  public String toString() {
    return getName();
  }

}