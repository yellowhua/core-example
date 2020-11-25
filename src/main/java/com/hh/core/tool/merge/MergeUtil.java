package com.hh.core.tool.merge;

import com.hh.core.tool.merge.data.ProductDataDto;

import java.util.ArrayList;
import java.util.List;

public class MergeUtil {

    /**
     * 功能说明：一对多数据，将多的一方合并在一起，形成一条数据
     * 原始数据：
     * 有多条数据，product跟model是一对多，并且这些数据根据product字段进行排序
     * 结果数据：
     * product相同的数据，model被合并在一起形成一条数据
     *
     * 功能扩展：如果需求还需要限制合并model的个数，那么可以传入length参数进行判断
     *
     * @param list 原始数据
     * @return 结果数据
     */
    public static List<ProductDataDto> mergeData(List<ProductDataDto> list) {
        List<ProductDataDto> result = new ArrayList<>();

        // 如果只有一条数据，不做处理
        if (list.size() == 1) { return list; }

        for (int i = 0; i < list.size() - 1; i++) {
            ProductDataDto productDataDto = list.get(i);
            ProductDataDto productDataDto2 = list.get(i + 1);
            // 如果当前数据跟下一个数据的product相等，那么就把model拼接起来
            if (productDataDto.getProduct().equals(productDataDto2.getProduct())) {
                productDataDto2.setModel(productDataDto.getModel() + "/" + productDataDto2.getModel());
            } else {
                result.add(productDataDto);
            }
            if (i == list.size() - 2) {
                result.add(productDataDto2);
            }
        }

        return result;
    }

}
