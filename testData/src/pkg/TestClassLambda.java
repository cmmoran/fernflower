/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pkg;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;

public class TestClassLambda {

  public int field = 0;
  public int afield = 0;

  private static Totals[] mine = new Totals[] {new Totals()};

  private Map<String, TestClassLambda.Totals> totals = new HashMap<>();

  public String testLambda0() {
    List<Totals> ab = new ArrayList<>();
    ab.add(new Totals());
    ab.sort(Comparator.comparingInt((a) -> a.athing));

    List<String> a = this.getStringList(null);

    a.forEach((aa) -> {
      Totals last = this.totals.put(aa.intern(), new Totals());
    });
    a.forEach((aa) -> {
      mine[0] = this.totals.put(aa.intern(), new Totals());
    });
    this.totals.put("abc", new Totals());
    this.totals.put("def", new Totals());
    return "abc";
  }

  public static class Totals {
    public int athing;
    public boolean  bthing;
  }

  public int compareThings(String a) {
    return 0;
  }

  public List<String> getStringList(Object o) {
    return new ArrayList<>();
  }

  public Map<String, TestClassLambda.Totals> getMapping(String abc) {
    return new HashMap<>();
  }

  public String testLambda00() {
    return this.handleVerification(() -> {
      Map<String, String> response = this.doSmthn(this.field, this.afield);
      if(!response.containsKey("field")) {
        this.doSmthnElse(this.field, this.afield);
      }
      return response;
    });
  }

  public String testLambda000() {
    return this.handleVerification(() -> {
      Map<String, String> response = this.doSmthn(this.field, this.afield);
      if (!response.containsKey("field")) {
        this.doSmthnElse(this.field, this.afield);
        return response;
      } else {
        return this.handleElse(() -> {
          if (this.field == 0 && this.afield == 0) {
            Map<String, String> aThing = this.doSmthn(this.afield, this.afield);
            aThing.put("aaa", "bbb");
            return aThing.get("aaa");
          } else {
            return this.field == 1 ? this.getClass().getName() : String.valueOf(this.afield);
          }
        });
      }
    });
  }

  private Map<String, String> doSmthn(int a, int b) {
    return new HashMap<>();
  }
  private void doSmthnElse(int a, int b) {
  }

  private String handleVerification(Supplier<Map<String, String>> supplier) {
    return "";
  }

  private Map<String, String> handleElse(Supplier<String> supplier) {
    return new HashMap<>();
  }

  public void testLambda() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    int b = (int)(Math.random() * 5);

    list.forEach(n -> {
      int a = 2 * n;
      System.out.println(a + b + field);
    });
  }

  public void testLambda1() {
    int a = (int)(Math.random() * 5);
    Runnable r1 = () -> System.out.println("hello1" + a);
    Runnable r2 = () -> System.out.println("hello2" + a);
  }

  public void testLambda2() {
    AtomicInteger aa = new AtomicInteger(0);
    reduce(Math::max);
    reduce((left, right) -> Math.max(left, right));
    reduce((left, right) -> field == 1 ? field + left : field + right);
    reduce((left, right) -> aa.getAndSet(field == 1 ? field + left : field + right));
  }

  public void testLambda3() { // IDEA-127301
    reduce(Math::max);
  }

  public void testLambda4() {
    reduce(TestClassLambda::localMax);
  }

  public void testLambda5() {
    String x = "abcd";
    function(x::toString);
  }

  public void testLambda6() {
    List<String> list = new ArrayList<>();
    int bottom = list.size() * 2;
    int top = list.size() * 5;
    list.removeIf(s -> (bottom >= s.length() && s.length() <= top));
  }

  public static void testLambda7(Annotation[] annotations) {
    Arrays.stream(annotations).map(Annotation::annotationType);
  }

  public static OptionalInt reduce(IntBinaryOperator op) {
    return null;
  }

  public static String function(Supplier<String> supplier) {
    return supplier.get();
  }

  public static int localMax(int first, int second) {
    return 0;
  }

  public void nestedLambdas() {
    int a =5;
    Runnable r1 = () -> {
      Runnable r2 = () -> System.out.println("hello2" + a);
      System.out.println("hello1" + a);
    };
  }
}
