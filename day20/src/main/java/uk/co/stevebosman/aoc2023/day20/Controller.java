package uk.co.stevebosman.aoc2023.day20;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Controller {
  public static final Deque<Message> messages = new ArrayDeque<>();
  public static final Map<Pulse, Long> counts = new ConcurrentHashMap<>();
  private static int index = 0;

  public static void pushTheButton(final Button button) {
    button.broadcast(Pulse.Low);
    while (!messages.isEmpty()) {
      final var message = messages.removeFirst();
      final Pulse pulse = message.pulse();

      if (Set.of("tx", "gc", "vg", "kp").contains(message.sender().getName()) && pulse == Pulse.High) {
        System.out.println(index + " " + message.sender());
      }

      counts.put(pulse, counts.getOrDefault(pulse, 0L) + 1);
      message.receiver().receivePulse(message.sender(), pulse);
    }
  }

  public static long pushTheButton(final Button button, final int count) {
    for (int i = 1; i <= count; i++) {
      Controller.index = i;
      pushTheButton(button);
    }
    return counts.get(Pulse.Low) * counts.get(Pulse.High);
  }
}
