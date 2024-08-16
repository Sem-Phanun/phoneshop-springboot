package com.project.phone_shop.util;

import java.util.ArrayList;
import java.util.List;

public class GeneralUtil {
    public static List<Integer> toIntegerList(List<String> list) {
        return list.stream()
                .map(x -> x.length())
                .toList();
    }
    public static List<Integer> getEnvenNumber(List<Integer> list) {
        return list.stream()
                .filter(x -> x % 2 == 0)
                .toList();
    }
}
