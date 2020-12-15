package com.hh.core.layui.eletree.service.impl;

import com.hh.core.layui.eletree.service.EletreeService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author huanghua
 * @date 2020/12/15
 */
@Service
public class EletreeServiceImpl implements EletreeService {

    @Override
    public List<Map<String, Object>> loadZwData() {
        List<Map<String, String>> syscodeList = new ArrayList<>();
        Map<String, String> syscode1 = new HashMap<>();
        syscode1.put("code", "1");
        syscode1.put("codename", "parent1");
        syscodeList.add(syscode1);
        Map<String, String> syscode101 = new HashMap<>();
        syscode101.put("code", "101");
        syscode101.put("codename", "parent1-son1");
        syscodeList.add(syscode101);
        Map<String, String> syscode102 = new HashMap<>();
        syscode102.put("code", "102");
        syscode102.put("codename", "parent1-son2");
        syscodeList.add(syscode102);
        Map<String, String> syscode2 = new HashMap<>();
        syscode2.put("code", "2");
        syscode2.put("codename", "parent2");
        syscodeList.add(syscode2);
        Map<String, String> syscode201 = new HashMap<>();
        syscode201.put("code", "201");
        syscode201.put("codename", "parent2-son1");
        syscodeList.add(syscode201);
        Map<String, String> syscode202 = new HashMap<>();
        syscode202.put("code", "202");
        syscode202.put("codename", "parent2-son2");
        syscodeList.add(syscode202);
        return buildLayuiData(syscodeList);
    }

    private List<Map<String, Object>> buildLayuiData(List<Map<String, String>> sysCodes) {
        List<Map<String, Object>> data = new LinkedList<>();
        List<Map<String, Object>> oneChildren = new LinkedList<>();
        List<Map<String, Object>> twoChildren = new LinkedList<>();
        List<Map<String, Object>> threeChildren = new LinkedList<>();
        for (Map<String, String> sysCode : sysCodes) {
            if (sysCode.get("code").length() == 1) {
                Map<String, Object> one = new HashMap<>();
                data.add(one);
                one.put("id", sysCode.get("code"));
                one.put("title", sysCode.get("codename"));

                oneChildren = new LinkedList<>();
                one.put("children", oneChildren);
            }

            if (sysCode.get("code").length() == 3) {
                Map<String, Object> two = new HashMap<>();
                oneChildren.add(two);
                two.put("id", sysCode.get("code"));
                two.put("title", sysCode.get("codename"));

                twoChildren = new LinkedList<>();
                two.put("children", twoChildren);
            }

            if (sysCode.get("code").length() == 5) {
                Map<String, Object> three = new HashMap<>();
                twoChildren.add(three);
                three.put("id", sysCode.get("code"));
                three.put("title", sysCode.get("codename"));

                threeChildren = new LinkedList<>();
                three.put("children", threeChildren);
            }

            if (sysCode.get("code").length() == 7) {
                Map<String, Object> four1 = new HashMap<>();
                threeChildren.add(four1);
                four1.put("id", sysCode.get("code"));
                four1.put("title", sysCode.get("codename"));
            }
        }
        return data;
    }

}
