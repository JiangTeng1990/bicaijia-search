package com.bicaijia.bcjsearch.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StringUtil {
    public static List<String> spiltToString(String str, String separator)
            throws Exception {
        if (null == str) {
            throw new Exception("str is null.");
        }
        List<String> l = new ArrayList<String>();
        if (null == separator) {
            l.add(str);
            return l;
        }
        String[] splitArray = str.trim().split(separator);
        for (String subStr : splitArray)
            l.add(subStr);
        return l;
    }

    public static String[] spiltToStringArray(String str, String separator)
            throws Exception {
        if (null == str) {
            throw new Exception("str is null.");
        }
        String[] splitArray = str.trim().split(separator);
        return splitArray;
    }

    public static List<Long> spiltToLong(String str, String separator)
            throws Exception {
        if (null == str) {
            throw new Exception("str is null.");
        }
        List<Long> l = new ArrayList<Long>();
        if (null == separator) {
            l.add(Long.parseLong(str));
            return l;
        }
        String[] splitArray = str.trim().split(separator);
        for (String numStr : splitArray)
            l.add(Long.parseLong(numStr));
        return l;
    }

    public static List<Integer> spiltToInteger(String str, String separator)
            throws Exception {
        if (null == str) {
            throw new Exception("str is null.");
        }
        List<Integer> l = new ArrayList<Integer>();
        if (null == separator) {
            l.add(Integer.parseInt(str));
            return l;
        }
        String[] splitArray = str.trim().split(separator);
        for (String numStr : splitArray)
            l.add(Integer.parseInt(numStr));
        return l;
    }

    public static List<Double> spiltToDouble(String str, String separator)
            throws Exception {
        if (null == str) {
            throw new Exception("str is null.");
        }
        List<Double> l = new ArrayList<Double>();
        if (null == separator) {
            l.add(Double.parseDouble(str));
            return l;
        }
        String[] splitArray = str.trim().split(separator);
        for (String numStr : splitArray)
            l.add(Double.parseDouble(numStr));
        return l;
    }

    public static boolean isEmptyString(String string) {
        if (null == string || "".equals(string.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 切割类似"price:asc,sell:desc"这样的字符串成为map
     * 
     * @param str
     * @param mainSeparator
     *            类似上面的“,”
     * @param subSeparator
     *            类似上面的“:”
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> splitToMap(String str,
            String mainSeparator, String subSeparator) throws Exception {
        if (isEmptyString(str)) {
            return Collections.EMPTY_MAP;
        }
        String[] strArray = str.trim().split(mainSeparator);
        Map<String, String> map = new HashMap<String, String>(strArray.length);

        for (String kv : strArray) {
            String[] kvArray = kv.trim().split(subSeparator);
            if (kvArray.length != 2 || isEmptyString(kvArray[0])
                    || isEmptyString(kvArray[1])) {
                throw new Exception("the format of str is error, error: \""
                        + kv + "\" in " + str);
            }
            map.put(kvArray[0], kvArray[1].toLowerCase());
        }
        return map;
    }

    public static String toListStatement(String pre, List<?> list,
            String splitTag, String end) {
        if (null == list || 0 >= list.size() || null == splitTag) {
            return null;
        }
        pre = (null == pre) ? "" : pre;
        end = (null == end) ? "" : end;

        StringBuilder builder = new StringBuilder();
        builder.append(pre);
        boolean isFirst = true;

        for (Object obj : list) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(splitTag);
            }
            builder.append(obj);
        }
        builder.append(end);
        return builder.toString();
    }

    // 将str转为list
    public static List<String> toList(String str, String splitChar) {
        if (Assert.isEmptyString(str)) {
            return null;
        }
        String[] array = str.split(splitChar);
        List<String> list = new ArrayList<String>(array.length);
        list.addAll(Arrays.asList(array));
        return list;
    }

    // 将a-b转为[a b]
    public static String toRangeStr(String str, String splitChar) {
        if (Assert.isEmptyString(str)) {
            return null;
        }
        String[] array = str.split(splitChar);
        return "[" + array[0] + " " + array[1] + "]";
    }

    /**
     * 将类似“0001010011001”中为1的索引存到int型的数组 中
     * 
     * @param binaryArray
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static List<Integer> strIndexToIntList(String str, char indexStr)
            throws Exception {
        if (null == str) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < str.length(); i++) {
            if (indexStr == str.charAt(i)) {
                list.add(i);
            }
        }
        return list;
    }

    public static String trim(String str, String prefix, String suffix)
            throws Exception {
        if (null == str) {
            return null;
        }
        int startIndex = 0;
        int endIndex = str.length();
        if (str.startsWith(prefix)) {
            startIndex = prefix.length();
        }
        if (str.endsWith(suffix)) {
            endIndex = str.length() - suffix.length();
        }
        return str.substring(startIndex, endIndex);
    }
}
