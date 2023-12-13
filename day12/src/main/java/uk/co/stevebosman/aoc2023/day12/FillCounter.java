package uk.co.stevebosman.aoc2023.day12;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FillCounter {
  public long count(final String input, final List<Integer> counts) {
    System.out.println("input = " + input);

    final String seeking = "[.?]*" + counts.stream()
                                           .map(i -> "[#?]{" + i + "}")
                                           .collect(Collectors.joining("[.?]+")) + "[.?]*";
    System.out.println("seeking = " + seeking);

    final Pattern p = Pattern.compile(seeking);

    Deque<String> active = new ArrayDeque<>();
    active.add(input);
    int count = 0;
    int currentI = 0;
    int hashCode = input.hashCode();
    int iLength = input.length();

    int i = 0;
    while (!active.isEmpty()) {
      i++;
      final String s = active.removeFirst();
      final String hashString = s.replaceFirst("\\?", "#");
      if (p.matcher(hashString).matches()) {
        if (hashString.contains("?")) {
          active.add(hashString);
        } else {
          count++;
        }
      }

      final String dotString = s.replaceFirst("\\?", ".");
      if (p.matcher(dotString).matches()) {
        if (dotString.contains("?")) {
          active.add(dotString);
        } else {
          count++;
        }
      }

      if (i % 10_000 == 0) {
        if (s.indexOf("?") != currentI) {
          currentI = s.indexOf("?");
          System.out.println(
                  LocalDateTime.now() + " - " + currentI + "/" + iLength + ": count = " + count + ", active = " + active.size() + " [" + hashCode + "]");
        }
      }
    }

    return count;
  }

  public long rawCount(final String raw) {
    final String[] s = raw.split(" ");
    return count(s[0], Arrays.stream(s[1].split(","))
                             .map(Integer::valueOf)
                             .toList());
  }

  public long countAll(final Stream<String> lines) {
    return lines.map(this::rawCount)
                .reduce(Long::sum)
                .orElse(0L);
  }

  public long countAll2(final Stream<String> lines) {
    List<String> strings = lines.toList();
    long sum = 0;
    for (int i = 0; i < strings.size(); i++) {
      System.out.println(i + "/" + strings.size());
      sum += count2(strings.get(i));
    }
    return sum;
//    return lines.parallel().map(this::count2).reduce(Long::sum).orElse(0L);
  }

  public long count2(final String raw) {
    final String[] s = raw.split(" ");
    List<Integer> counts = Arrays.stream(s[1].split(","))
                                 .map(Integer::valueOf)
                                 .toList();
    List<Integer> longCounts = new ArrayList<>();
    longCounts.addAll(counts);
    longCounts.addAll(counts);
    longCounts.addAll(counts);
    longCounts.addAll(counts);
    longCounts.addAll(counts);

    return count(s[0] + "?" + s[0] + "?" + s[0] + "?" + s[0] + "?" + s[0], longCounts);
  }

}
