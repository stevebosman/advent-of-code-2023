package uk.co.stevebosman.aoc2023.day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Boxes {
  public static int totalFocusingPower(final String input) {
    List<Order> orders = Arrays.stream(input.replaceAll("\n", "").split(",")).map(Order::of).toList();

    final List<List<Order>> boxes = createEmptyBoxes();

    assignOrders(orders, boxes);

    return IntStream.range(0, 256).map(i -> focusingPower(boxes, i)).reduce(Integer::sum).orElse(0);
  }

  private static int focusingPower(List<List<Order>> boxes, int i) {
    List<Order> box = boxes.get(i);
    int total = 0;
    for (int j = 0; j < box.size(); j++) {
      total += (i+1) * (j + 1) * box.get(j).focalLength();
    }
    return total;
  }

  private static void assignOrders(List<Order> orders, List<List<Order>> boxes) {
    for (Order order : orders) {
      final List<Order> box = boxes.get(order.box());
      final int exists = indexOf(order, box);
      if (order.orderType() == OrderType.Remove) {
        if (exists>=0) box.remove(exists);
      } else if (exists < 0) {
        box.add(order);
      } else {
        box.set(exists, order);
      }
    }
  }

  private static int indexOf(Order order, List<Order> box) {
    int result = -1;
    for (int i = 0; i < box.size(); i++) {
      if (box.get(i).label().equals(order.label())) {
        result = i;
        break;
      }
    }
    return result;
  }

  private static List<List<Order>> createEmptyBoxes() {
    final List<List<Order>> boxes = new ArrayList<>(256);
    IntStream.range(0, 256).forEach(i -> boxes.add(new ArrayList<>()));
    return boxes;
  }
}
