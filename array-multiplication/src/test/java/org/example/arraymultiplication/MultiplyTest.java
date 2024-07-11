/*
 *Copyright (C) 2024 Claudio Nave
 *
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation, either version 3 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.example.arraymultiplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class MultiplyTest {
  private static Random random;

  @BeforeAll
  static void setUpRandom() {
    var seed = System.currentTimeMillis();
    System.out.println("Seed: " + seed);
    random = new Random(seed);
  }

  @RepeatedTest(1000)
  void multiplyRandomTest() {
    var a = random.nextInt(-1000, 1000);
    var b = random.nextInt(-1000, 1000);
    var result = Multiply.multiply(a, b);
    assertEquals(Long.toString(a * (long) b), result, "Multiply failed for " + a + " and " + b);
  }

  @Test
  void multiplyFirstZeroTest() {
    var result = Multiply.multiply(0, 42);
    assertEquals("0", result);
  }

  @Test
  void multiplySecondZeroTest() {
    var result = Multiply.multiply(42, 0);
    assertEquals("0", result);
  }
}
