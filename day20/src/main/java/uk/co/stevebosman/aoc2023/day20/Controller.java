package uk.co.stevebosman.aoc2023.day20;

import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Controller {
  private static final Deque<Message> messages = new ArrayDeque<>();
  private static final Map<Pulse, Long> counts = new ConcurrentHashMap<>();
  private static final Set<String> RX_INPUTS = Set.of("tx", "gc", "vg", "kp");
  private static final Map<String, List<Long>> RX_OCCURRENCES = new HashMap<>();
  private static long index = 0;


  public static void addMessage(final Message message) {
    final Pulse pulse = message.pulse();
    counts.put(pulse, counts.getOrDefault(pulse, 0L) + 1);

    final String senderName = message.sender()
                         .getName();
    if ("bq".equals(message.receiver().getName())
            && RX_INPUTS.contains(senderName)
            && pulse == Pulse.High) {
      RX_OCCURRENCES.computeIfAbsent(senderName,
                                     (k)->new ArrayList<>())
                    .add(index);
    }

    messages.add(message);
  }

  public static void pushTheButton(final Button button) {
    button.broadcast(Pulse.Low);
    while (!messages.isEmpty()) {
      final var message = messages.removeFirst();
      final Pulse pulse = message.pulse();
      message.receiver()
             .receivePulse(message.sender(), pulse);
    }
  }

  public static long pushTheButton(final Button button, final int count) {
    for (int i = 1; i <= count; i++) {
      Controller.index = i;
      pushTheButton(button);
    }
    return counts.get(Pulse.Low) * counts.get(Pulse.High);
  }

  public static long getLowPulseToRxTime() {
    final List<Long> rxRepeats = getRxRepeats(4);
    long result = ArithmeticUtils.lcm(rxRepeats.get(0), rxRepeats.get(1));
    for (int i = 2; i < rxRepeats.size(); i++) {
      result = ArithmeticUtils.lcm(result, rxRepeats.get(i));
    }
    return result;
  }

  private static List<Long> getRxRepeats(final int minimumCount) {
    if (minimumCount > 0 && RX_OCCURRENCES.size() < RX_INPUTS.size()) {
      throw new IllegalStateException("Too few entries in " + RX_OCCURRENCES);
    }
    final List<Long> result = new ArrayList<>();
    for (final Map.Entry<String, List<Long>> entry : RX_OCCURRENCES.entrySet()) {
      final String name = entry.getKey();
      final List<Long> list = entry.getValue();
      if (list.size() < minimumCount) {
        throw new IllegalStateException("Less than " + minimumCount + " elements in " + list + " for " + name);
      }
      verifyMultiplesOfFirst(name, list);
      result.add(list.getFirst());
    }
    return result;
  }


  private static void verifyMultiplesOfFirst(final String name, final List<Long> list) {
    final long multiples = list.stream()
                               .filter(l -> l % list.getFirst() == 0)
                               .count();
    if (multiples != list.size()) {
      throw new IllegalStateException("No repeats for " + name + " in " + list);
    }
  }
}
