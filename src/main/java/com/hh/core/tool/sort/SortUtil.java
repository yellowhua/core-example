package com.hh.core.tool.sort;

import com.hh.core.tool.sort.domain.User;
import org.apache.commons.collections4.CollectionUtils;

import java.text.Collator;
import java.util.*;

import static java.util.Locale.CHINA;

/**
 * Created by hh on 2018/8/1.
 * 排序工具类
 */
public class SortUtil {

    /**
     * 根据数组--》值的--》中文拼音首字母排序
     * @param arr 数组
     * @return 排序结果
     */
    public static String[] sortArr(String[] arr) {
        if (arr == null || arr.length == 0) {return arr;}
        Comparator<Object> comparator = Collator.getInstance(CHINA);
        Arrays.sort(arr, comparator);
        return arr;
    }

    /**
     * 根据对象集合--》对象属性--》中文拼音首字母排序
     * @param list 对象集合
     * @return 排序结果
     */
    public static List<User> sortObjectListOne(List<User> list) {
        if (CollectionUtils.isEmpty(list)) {return list;}
        Map<String, User> map = new HashMap<>();
        String[] names = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String name = user.getName();
            names[i] = name;
            map.put(name, user);
        }
        names = sortArr(names);
        list.clear();
        for (String name : names) {
            if (map.containsKey(name)) {
                list.add(map.get(name));
            }
        }
        return list;
    }

    /**
     * 根据对象集合--》对象属性--》中文拼音首字母排序
     * @param list 对象集合
     * @return 排序结果
     */
    public static List<User> sortObjectListTwo(List<User> list) {
        Collections.sort(list, (o1, o2) -> {
            Comparator<Object> com = Collator.getInstance(Locale.CHINA);
            return ((Collator) com).compare(o1.getName(),o2.getName());
        });
        return list;
    }

    /**
     * 根据map集合--》map值--》中文拼音首字母排序
     * @param list map对象集合
     * @return
     */
    public static List<Map<String, Object>> sortMapList(List<Map<String, Object>> list) {
        if (CollectionUtils.isEmpty(list)) {return list;}
        Map<String, Map<String, Object>> allMap = new HashMap<>();
        String[] values = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String key = new ArrayList<>(map.keySet()).get(0);
            String value = String.valueOf(map.get(key));
            values[i] = value;
            allMap.put(value, map);
        }
        values = sortArr(values);
        list.clear();
        for (String value : values) {
            if (allMap.containsKey(value)) {
                list.add(allMap.get(value));
            }
        }
        return list;
    }

}
