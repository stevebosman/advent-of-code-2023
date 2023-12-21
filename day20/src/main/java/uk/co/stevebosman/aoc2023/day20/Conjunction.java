package uk.co.stevebosman.aoc2023.day20;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Conjunction extends Module {
  private final Map<Module, Pulse> inputStates = new HashMap<>();

  public Conjunction(final String name) {
    super(name);
  }

  public String toString() {
    return "&" + getName();
  }

  @Override
  public void receivePulse(final Module sender, final Pulse pulse) {
    if (sender.getName().equals("tx") && pulse == Pulse.High) {
      System.out.println(Module.index + " " + sender);
    }
    inputStates.put(sender, pulse);
    if (inputStates.containsValue(Pulse.Low)) {
      broadcast(Pulse.High);
    } else {
      broadcast(Pulse.Low);
    }
  }

  public void registerInput(final Module module) {
    inputStates.put(module, Pulse.Low);
  }
}
