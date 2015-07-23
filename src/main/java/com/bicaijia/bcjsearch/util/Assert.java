package com.bicaijia.bcjsearch.util;

import java.util.List;
import java.util.Map;

public class Assert {
    public static boolean isEmptyString(String string) {
        if (null == string || "".equals(string.trim())) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyString(String string) {
        return !isEmptyString(string);
    }

    public static boolean isEmptyList(List list) {
        if (null == list || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyList(List list) {
        return !isEmptyList(list);
    }

    public static boolean isEmptyMap(Map map) {
        if (null == map || map.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyMap(Map map) {
        return !isEmptyMap(map);
    }

    // 2个float是否相等。
    public static boolean equalsFloat(Float floatA, Float floatB) {
        if (null == floatA || null == floatB) {
            return false;
        }
        if (Math.abs(floatA - floatB) < 0.01) {
            return true;
        }
        return false;
    }

    // 2个Double是否相等。
    public static boolean equalsDouble(Double doubleA, Double doubleB) {
        if (null == doubleA || null == doubleB) {
            return false;
        }
        if (Math.abs(doubleA - doubleB) < 0.01) {
            return true;
        }
        return false;
    }

    // 某日期是否在日期区间内。
    public static boolean betweenDate(String dateStr, String beginDateStr,
            String endDateStr) {
        if (dateStr.compareTo(beginDateStr) >= 0
                && dateStr.compareTo(endDateStr) <= 0) {
            return true;
        }
        return false;
    }

    // 某时间是否在时间区间内。
    public static boolean betweenTime(String timeStr, String beginTimeStr,
            String endTimeStr) {
        if (timeStr.compareTo(beginTimeStr) >= 0
                && timeStr.compareTo(endTimeStr) <= 0) {
            return true;
        }
        return false;
    }

}
