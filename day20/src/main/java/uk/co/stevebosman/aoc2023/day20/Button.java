package uk.co.stevebosman.aoc2023.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Button extends Module {
  public Button(final List<String> lines) {
    super("button");
    final Map<String, Module> modules = new HashMap<>();
    final Map<String, String> rawModules = new HashMap<>();

    // Build modules
    for (final String line: lines) {
      final String[] raw = line.split("->");
      final String name = raw[0].strip();
      if (name.startsWith("%")) {
        final String trueName = name.substring(1);
        final FlipFlop module = new FlipFlop(trueName);
        modules.put(trueName, module);
        rawModules.put(trueName, raw[1].strip());
      } else if (name.startsWith("&")) {
        final String trueName = name.substring(1);
        final Conjunction module = new Conjunction(trueName);
        modules.put(trueName, module);
        rawModules.put(trueName, raw[1].strip());
      } else if ("broadcaster".equals(name)) {
        final Broadcaster broadcaster = new Broadcaster();
        registerOutput(broadcaster);
        modules.put(name, broadcaster);
        rawModules.put(name, raw[1].strip());
      }
    }
    final Conjunction rx = new Conjunction("rx");
    modules.put("rx", rx);
    // Route modules
    for(final Map.Entry<String, String> entry: rawModules.entrySet()) {
      final Module module = modules.get(entry.getKey());
      final String[] outputs = entry.getValue().split(", ");
      for (final String output: outputs) {
        final Module outputModule = modules.get(output);
        if (outputModule == null) {
          throw new IllegalArgumentException("Cannot find '" + output + "'");
        }
        module.registerOutput(outputModule);
        if (outputModule instanceof Conjunction) {
          ((Conjunction)outputModule).registerInput(module);
        }
      }
    }
  }

  @Override
  public void receivePulse(final Module sender, final Pulse pulse) {
    // do nothing
  }

  public long repeat(final long count) {
    for (long i = 0; i < count; i++) {
      Module.index++;
      broadcast(Pulse.Low);
    }
    return Module.counts.get(Pulse.Low) * Module.counts.get(Pulse.High);
  }

  public String toString() {
    return "button";
  }
}
