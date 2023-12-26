package uk.co.stevebosman.aoc2023.day22;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Brick implements Comparable<Brick> {
  private final String name;
  private final Position3d p1;
  private final Position3d p2;
  private final Set<Position> horizontalPositions;
  private final int bottom;
  public static final Comparator<Brick> TOP_DOWN = (o1, o2) -> {
    int result = Position3d.TOP_DOWN.compare(o1.p2, o2.p2);
    if (result == 0) result = Position3d.TOP_DOWN.compare(o1.p1, o2.p1);
    return result;
  };

  public Brick(final int index, final Position3d p1, final Position3d p2) {
    this(String.valueOf(index), p1, p2);
  }

  public Brick(final String name, final Position3d p1, final Position3d p2) {
    this.name = name;
    this.p1 = (p1.compareTo(p2) < 0)
              ? p1
              : p2;
    this.p2 = (p1.compareTo(p2) < 0)
              ? p2
              : p1;
    this.horizontalPositions = calculateHorizontalPositions(this.p1, this.p2);
    this.bottom = Math.min(p1.z(), p2.z());
  }

  public static Brick of(final int index, final String input) {
    final var values = input.split("~");
    final var p1 = Position3d.of(values[0]);
    final var p2 = Position3d.of(values[1]);
    return new Brick(index, p1, p2);
  }

  public static Set<Position> calculateHorizontalPositions(final Position3d p1, final Position3d p2) {
    final var result = new HashSet<Position>();
    if (p1.x() == p2.x()) {
      for (int i = p1.y(); i <= p2.y(); i++) {
        result.add(new Position(p1.x(), i));
      }
    } else {
      for (int i = p1.x(); i <= p2.x(); i++) {
        result.add(new Position(i, p1.y()));
      }
    }
    return result;
  }

  public Set<Position> horizontalPositions() {
    return horizontalPositions;
  }

  public int bottom() {
    return bottom;
  }

  @Override
  public int compareTo(final Brick o) {
    int result = p1.compareTo(o.p1);
    if (result == 0) result = p2.compareTo(o.p2);
    return result;
  }

  public Brick dropTo(final int z) {
    return new Brick(this.name, new Position3d(p1.x(), p1.y(), z), new Position3d(p2.x(), p2.y(), z + p2.z() - p1.z()));
  }

  public boolean horizontalIntersects(final Brick brick) {
    return !Collections.disjoint(brick.horizontalPositions(), horizontalPositions());
  }

  public String toString() {
    return name + "[" + p1 + "~" + p2 + "]";
  }

  public Position3d p1() {
    return p1;
  }

  public Position3d p2() {
    return p2;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    final var that = (Brick) obj;
    return Objects.equals(this.p1, that.p1) &&
            Objects.equals(this.p2, that.p2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(p1, p2);
  }
}
