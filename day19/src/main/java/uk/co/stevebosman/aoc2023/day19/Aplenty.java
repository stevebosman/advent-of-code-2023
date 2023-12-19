package uk.co.stevebosman.aoc2023.day19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Aplenty {
  private final List<Part> parts;
  private final Map<String, Workflow> workflowCache = new ConcurrentHashMap<>();

  public Aplenty(final List<String> lines) {
    final List<Part> parts = new ArrayList<>();
    boolean workflows = true;
    for(final String line: lines) {
      if (line.isBlank()) {
        workflows = false;
      } else if (workflows) {
        final var workflow = Workflow.of(line);
        workflowCache.put(workflow.name(), workflow);
      } else {
        parts.add(Part.of(line));
      }
    }
    this.parts = Collections.unmodifiableList(parts);
  }

  public long runAll() {
    return parts.parallelStream().filter(this::partAccepted).map(Part::totalRatings).reduce(Long::sum).orElse(0L);
  }

  private boolean partAccepted(final Part part) {
    Workflow current = workflowCache.get("in");

    final boolean result;
    while (true) {
      final String next = current.process(part);
      if ("A".equals(next)) {
        result = true;
        break;
      } else if ("R".equals(next)) {
        result = false;
        break;
      }
      current = workflowCache.get(next);
    }

    return result;
  }
}
