package uk.co.stevebosman.aoc2023.day19;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WorkflowCache {
  private final Map<String, Workflow> workflowCache = new ConcurrentHashMap<>();

  public void add(final Workflow workflow) {
    workflowCache.put(workflow.name(), workflow);
  }

  public Workflow get(final String name) {
    return workflowCache.get(name);
  }
}
