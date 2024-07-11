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

public class Multiply {
  public static String multiply(int a, int b) {
    if (a == 0 || b == 0) {
      return "0";
    }
    var addend = Math.max(Math.abs(a), Math.abs(b));
    var counter = Math.min(Math.abs(a), Math.abs(b));
    var resultMaxLength = (int) (Math.log10(addend) + 1) * 2;
    var result = multiply(addend, counter, resultMaxLength);
    return a > 0 ^ b > 0 ? "-" + result : result;
  }

  private static String multiply(int addend, int counter, int resultMaxLength) {
    var result = digits(addend, resultMaxLength);
    var addendArray = digits(addend, resultMaxLength);
    for (int i = 1; i < counter; i++) {
      result = add(result, addendArray);
    }
    return format(result);
  }

  private static int[] add(int[] a, int[] b) {
    var result = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      result[i] = a[i] + b[i];
    }
    return normalize(result);
  }

  private static int[] normalize(int[] n) {
    var remainder = 0;
    for (int i = 0; i < n.length; i++) {
      var buffer = n[i] + remainder;
      remainder = buffer / 10;
      n[i] = buffer % 10;
    }
    return n;
  }

  private static String format(int[] n) {
    var result = 0;
    for (int i = 0; i < n.length; i++) {
      result += (int) (n[i] * Math.pow(10, i));
    }
    return Integer.toString(result);
  }

  private static int[] digits(int n, int length) {
    var array = new int[length];
    var buffer = n;
    for (int i = 0; i < length; i++) {
      array[i] = buffer % 10;
      buffer /= 10;
    }
    return array;
  }
}
