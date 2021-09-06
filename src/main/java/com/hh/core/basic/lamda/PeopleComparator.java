package com.hh.core.basic.lamda;

import java.util.Comparator;

/**
 * Created by hh on 2020/1/2.
 * 比较类
 */
public class PeopleComparator implements Comparator<People> {

    @Override
    public int compare(People p1, People p2) {
        if (p1.getAge() > p2.getAge()) {
            return 1;
        } else if (p1.getAge() == p2.getAge()) {
            return 0;
        } else {
            return -1;
        }
    }
}
