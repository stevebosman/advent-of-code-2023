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
    // Route modules
    for(final Map.Entry<String, String> entry: rawModules.entrySet()) {
      final Module module = modules.get(entry.getKey());
      final String[] outputs = entry.getValue().split(", ");
      for (final String output: outputs) {
        final Module outputModule = modules.getOrDefault(output, new Conjunction(output));
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

  public String toString() {
    return "button";
  }
}
