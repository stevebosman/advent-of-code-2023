package uk.co.stevebosman.aoc2023.day15;

public record Order(int box, String label, OrderType orderType, Integer focalLength) {
  public static Order of(String step) {
    final OrderType orderType;
    final String label;
    final Integer focalLength;
    if (step.endsWith("-")) {
      orderType = OrderType.Remove;
      label=step.substring(0, step.indexOf('-'));
      focalLength = null;
    } else {
      orderType = OrderType.Add;
      final int equalsPos = step.indexOf('=');
      label=step.substring(0, equalsPos);
      focalLength = Integer.valueOf(step.substring(equalsPos+1));
    }
    int box = Hash.hash(label);
    return new Order(box, label, orderType, focalLength);
  }
}
