package uk.co.stevebosman.aoc2023.day20;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Module {
  private final String name;
  private final Set<Module> outputs = new HashSet<>();
  static Map<Pulse, Long> counts = new ConcurrentHashMap<>();

  protected Module(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract void receivePulse(Module sender, Pulse pulse) ;

  public void broadcast(final Pulse pulse) {
    counts.put(pulse, counts.getOrDefault(pulse, 0L) + outputs.size());
    final Module sender = this;
    for (final Module module : outputs) {
      System.out.println(sender + " " + pulse + "->" + module);
      module.receivePulse(sender, pulse);
    }
  }

  public void registerOutput(final Module module) {
    this.outputs.add(module);
  }
}
