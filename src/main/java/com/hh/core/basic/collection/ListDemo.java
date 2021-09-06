package com.hh.core.basic.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 描述：list集合
 * </pre>
 *
 * @类名： com.hh.core.basic.collection.ListDemo
 * @作者： huanghua
 * @创建日期: 2021/9/6 11:11
 */
@Slf4j
public class ListDemo {

    /**
     * 使用arrays
     */
    public static void arrToList() {
        String[] arr = new String[]{"a", "b", "c"};
        List<String> list = Arrays.asList(arr);

        // 只能查改，不能增删，抛异常
//        list.add("d");

        log.info("{}", list);
    }

    /**
     * 使用arrays，转换为ArrayList
     */
    public static void arrToList2() {
        String[] arr = new String[]{"a", "b", "c"};
        List<String> list = new ArrayList<>(Arrays.asList(arr));

        // 可以进行增删改查
        list.add("d");

        log.info("{}", list);
    }

    /**
     * 使用Collections
     */
    public static void arrToList3() {
        String[] arr = new String[]{"a", "b", "c"};
        List<String> list = new ArrayList<>(arr.length);
        Collections.addAll(list, arr);

        // 可以进行增删改查
        list.add("d");

        log.info("{}", list);
    }

    public static void main(String[] args) {
        arrToList3();
    }

}
