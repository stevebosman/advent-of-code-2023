package uk.co.stevebosman.aoc2023.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoxesTest {
  @Test
  void addOrder() {
    int actual = Boxes.totalFocusingPower("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7\n");
    assertEquals(145, actual);
  }
  @Test
  void addOrderWithLinefeed() {
    int actual = Boxes.totalFocusingPower("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7");
    assertEquals(145, actual);
  }
}
