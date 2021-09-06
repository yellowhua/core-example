package com.hh.core.basic.lamda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hh on 2020/1/2.
 * 比较类lamda
 */
public class PepleLamda {

    public static void main(String[] args) {
        // 调用接口排序
        List<People> peoples = People.initPeopleList();
        Collections.sort(peoples, new PeopleComparator());
        System.out.println(peoples);

        // 内部类排序
        class PeopleComparator implements Comparator<People> {
            @Override
            public int compare(People p1, People p2) {
                return p2.getAge() - p1.getAge();
            }
        }
        Collections.sort(peoples, new PeopleComparator());
        System.out.println(peoples);

        // lamda方式单条件排序
        Collections.sort(peoples, (p1, p2) -> p1.getAge() - p2.getAge());
        System.out.println(peoples);

        // lamda方式多条件排序
        Collections.sort(peoples, Comparator.comparing(People::getAge).thenComparing(People::getName));
        System.out.println(peoples);
    }

}
