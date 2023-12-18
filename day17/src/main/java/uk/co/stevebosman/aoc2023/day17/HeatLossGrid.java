package uk.co.stevebosman.aoc2023.day17;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class HeatLossGrid {
  public static int minimumHeatLoss(final List<String> lines, final int min, final int max) {
    final var grid = new Grid(lines);

    final var start = new Position(0, 0);
    final var end = new Position(grid.getMaxX(), grid.getMaxY());

    final var queue = new PriorityQueue<CostedState>();
    queue.add(new CostedState(new State(start, null, 0), 0));
    final var visited = new HashSet<State>();

    while (!queue.isEmpty()) {
      final var current = queue.poll();

      final var currentState = current.state();
      if (visited.contains(currentState)) continue;
      if (currentState.stepCount() >= min && currentState.position().equals(end)) {
        return current.cost();
      }
      visited.add(currentState);
      // continue in current direction
      if (currentState.stepCount() < max && currentState.direction() != null) {
        addToQueue(grid, queue, current, currentState, currentState.direction(), currentState.stepCount() + 1);
      }
      // try other directions
      if (currentState.stepCount()==0 || currentState.stepCount() >= min) {
        for (final Direction nextDirection : Direction.values()) {
          if (nextDirection == currentState.direction() || nextDirection.opposite() == currentState.direction()) {
            // skip current direction and reverse of current direction
            continue;
          }
          addToQueue(grid, queue, current, currentState, nextDirection, 1);
        }
      }
    }

    return 0;
  }

  private static void addToQueue(
          final Grid grid,
          final PriorityQueue<CostedState> queue,
          final CostedState current,
          final State currentState,
          final Direction nextDirection,
          final int nextStepCount
  ) {
    final Position next = currentState.position().move(nextDirection);
    final var block = grid.get(next);
    if (block.isPresent()) {
      final State state = new State(next, nextDirection, nextStepCount);
      queue.add(new CostedState(state, current.cost() + block.get()));
    }
  }
}

