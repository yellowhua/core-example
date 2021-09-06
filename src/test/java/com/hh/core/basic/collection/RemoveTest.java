package com.hh.core.basic.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by hh on 2020/3/17.
 */
public class RemoveTest {

    /**
     * 错误示例
     */
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        for (String x : list) {
            if ("a".equals(x)) {
                list.remove(x);
            }
        }

        System.out.println(list);
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        ListIterator iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("a")) {
                iterator.remove();
            }
        }

        System.out.println(list);
    }

    @Test
    public void test3() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("a")) {
                iterator.remove();
            }
        }

        System.out.println(list);
    }

}
