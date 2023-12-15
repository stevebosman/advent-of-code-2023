package uk.co.stevebosman.aoc2023.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderTest {
  @Test
  void removeOrder() {
    Order actual = Order.of("rm-");
    assertAll(
            ()->assertEquals(OrderType.Remove, actual.orderType()),
            ()->assertEquals("rm", actual.label()),
            ()->assertEquals(239, actual.box()),
            ()->assertNull(actual.focalLength())
    );
  }

  @Test
  void addOrder() {
    Order actual = Order.of("pc=12");
    assertAll(
            ()->assertEquals(OrderType.Add, actual.orderType()),
            ()->assertEquals("pc", actual.label()),
            ()->assertEquals(3, actual.box()),
            ()->assertEquals(12, actual.focalLength())
    );
  }
}
