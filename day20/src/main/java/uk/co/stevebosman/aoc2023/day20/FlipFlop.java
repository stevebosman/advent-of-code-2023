package uk.co.stevebosman.aoc2023.day20;

public final class FlipFlop extends Module {
  private State state = State.Off;

  public FlipFlop(final String name) {
    super(name);
  }

  @Override
  public void receivePulse(final Module sender, final Pulse pulse) {
    if (pulse == Pulse.Low) {
      if (state == State.Off) {
        state = State.On;
        broadcast(Pulse.High);
      } else {
        state = State.Off;
        broadcast(Pulse.Low);
      }
    }
  }

  public String toString() {
    return "%" + getName();
  }

}