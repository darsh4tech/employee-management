package com.emtion.employee.management.util;

import java.util.Collection;
import java.util.Map;

public class Utils {

  public static boolean isNotEmpty(Object value) {
    if (value instanceof Collection<?>) {
      return (Collection<?>) value != null && !((Collection<?>) value).isEmpty();

    } else if (value instanceof Map<?, ?>) {
      return (Map<?, ?>) value != null && !((Map<?, ?>) value).isEmpty();

    } else if (value instanceof String) {
      return value != null && !((String) value).trim().equals("");

    } else {
      return value != null;
    }
  }

}
