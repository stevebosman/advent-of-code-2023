package uk.co.stevebosman.aoc2023.day20;

public record Message(Module sender, Module receiver, Pulse pulse) {
  @Override
  public String toString() {
    return sender + " " + pulse + "->" + receiver;
  }
}
