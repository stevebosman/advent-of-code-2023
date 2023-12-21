package uk.co.stevebosman.aoc2023.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ButtonTest {
  @Test
  void example1() {
    final var input = """
            broadcaster -> a, b, c
            %a -> b
            %b -> c
            %c -> inv
            &inv -> a""";
    final var instance = new Button(input.lines().toList());

    assertEquals(32_000_000L, Controller.pushTheButton(instance, 1000));
  }

  @Test
  void example2() {
    final var input = """
            broadcaster -> a
            %a -> inv, con
            &inv -> b
            %b -> con
            &con -> output""";
    final var instance = new Button(input.lines().toList());

    assertEquals(11_687_500L, Controller.pushTheButton(instance, 1000));

  }
}
