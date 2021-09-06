package com.hh.core.basic.symbol;

import org.junit.Test;

/**
 * Created by hh on 2020/3/17.
 * 测试<<和>>和>>>
 */
public class SymbolTest {

    @Test
    public void test1() {
        int a = 1 << 4;
        int b = 16 >> 4;
        int c = 1 << 30;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    @Test
    public void test2() {
        int n = 16 - 1;
        System.out.println(n >>> 1);
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);

        int a = 2 ^ 3;
        System.out.println(a);
    }

}
