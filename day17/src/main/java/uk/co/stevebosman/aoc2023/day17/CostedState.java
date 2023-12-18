package uk.co.stevebosman.aoc2023.day17;

record CostedState(State state, int cost) implements Comparable<CostedState> {
  @Override
  public int compareTo(final CostedState o) {
    return Integer.compare(cost, o.cost);
  }
}
