package uk.co.stevebosman.aoc2023.day20;

import java.util.HashSet;
import java.util.Set;

public abstract class Module {
  private final String name;
  private final Set<Module> outputs = new HashSet<>();

  protected Module(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract void receivePulse(Module sender, Pulse pulse) ;

  public void broadcast(final Pulse pulse) {
    for (final Module module : outputs) {
      Controller.messages.add(new Message(this, module, pulse));
    }
  }

  public void registerOutput(final Module module) {
    this.outputs.add(module);
  }
}
