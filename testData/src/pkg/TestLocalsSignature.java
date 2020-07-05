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

import java.util.*;

public class TestLocalsSignature {
  private static final Map<String, String> m = new HashMap<>();
  private static final List<String> ss = new ArrayList<>();
  private List<String> sss = new ArrayList<>();
  public static void main(String[] args) {
    List<String> s = new ArrayList<>();
    s.add("xxx");

    if(s.size() > 1) {
      s.clear();
    }
    m.put("xxx", s.get(0));
    List<String> ss = getS();
    ss.add("xxx");

    Map<String, String> nm = getM();
    nm.put("xxx", m.get("xxx"));
  }

  public static List<String> getS() {
    return new ArrayList<>();
  }

  public static <T> List<T> getT() {
    return new ArrayList<>();
  }

  public static <K, V> Map<K,V> getM() {
    return new HashMap<>();
  }

  public static String localize(List<Locale> preferredLanguages, Map<Locale, String> strings, String defaultString) {
     if (!preferredLanguages.isEmpty()) {// 53
        Iterator var3 = preferredLanguages.iterator();

        while(var3.hasNext()) {
           Locale preferredLanguage = (Locale)var3.next();// 55
           String string = (String)strings.get((Locale)preferredLanguage);// 56
           if (string != null) {// 57
              return (String)string;// 58
           }

           if (preferredLanguage.getCountry() != null) {// 61
              string = (String)strings.get((Locale)new Locale(preferredLanguage.getLanguage()));// 62
              if (string != null) {// 63
                 return (String)string;// 64
              }
           }
        }
     }

     return (String)defaultString;// 70
  }

}
