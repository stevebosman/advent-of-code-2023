package uk.co.stevebosman.aoc2023.day22;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public record SandSlabs(TreeSet<Brick> bricks) {

  public static SandSlabs of(final List<String> lines) {
    final TreeSet<Brick> bricks = new TreeSet<>();
    for (int i = 0; i < lines.size(); i++) {
      bricks.add(Brick.of(i, lines.get(i)));
    }
    return new SandSlabs(bricks);
  }

  public SandSlabs drop() {
    log("intial", bricks, new TreeSet<>());
    final var dropped = new TreeSet<>(Brick.TOP_DOWN);
    for (final Brick b1 : bricks) {
      int z = 1;
      for (final Brick b2 : dropped) {
        if (b1.horizontalIntersects(b2)) {
          z = Math.max(b2.p1()
                         .z(),
                       b2.p2()
                         .z()) + 1;
          break;
        }
      }
      dropped.add(b1.dropTo(z));
    }

    log("dropped", dropped, new TreeSet<>());
    final TreeSet<Brick> naturalOrder = new TreeSet<>();
    naturalOrder.addAll(dropped);
    return new SandSlabs(naturalOrder);
  }

  private void log(final String desc, final TreeSet<Brick> bricks, final Set<Brick> highlights) {
    int z = 0;
    System.out.print(desc + " = [");
    for(final Brick brick: bricks) {
      if (brick.bottom() != z) {
        z = brick.bottom();
        System.out.println();
        System.out.print("  " + z + ": ");
      } else {
        System.out.print(", ");
      }
      if (highlights.contains(brick)) {
        System.out.print("*");
      }
      System.out.print(brick);
    }
    System.out.println("]");
  }

  public long canDisintegrateCount() {
    final TreeSet<Brick> bricks = drop().bricks;

    int count = 0;
    for(final Brick brick: bricks) {
      final var test = new TreeSet<>(bricks);
      test.remove(brick);
      if (!dropHappens(test)) count++;
    }

    return count;
  }

  public boolean dropHappens(final TreeSet<Brick> bricks) {
    final var dropped = new TreeSet<>(Brick.TOP_DOWN);
    for (final Brick b1 : bricks) {
      int z = 1;
      for (final Brick b2 : dropped) {
        if (b1.horizontalIntersects(b2)) {
          z = Math.max(b2.p1()
                         .z(),
                       b2.p2()
                         .z()) + 1;
          break;
        }
      }
      if (b1.bottom() != z) {
        return true;
      }
      dropped.add(b1);
    }

    return false;
  }
}
