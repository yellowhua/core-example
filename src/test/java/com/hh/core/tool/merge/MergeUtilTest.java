package com.hh.core.tool.merge;

import com.hh.core.tool.merge.data.ProductDataDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MergeUtilTest {

    @Test
    public void testMerge() {
        List<ProductDataDto> list = createData();
        List<ProductDataDto> result = MergeUtil.mergeData(list);
        for (ProductDataDto data : result) {
            System.out.println(data);
        }
    }

    private List<ProductDataDto> createData() {
        List<ProductDataDto> list = new ArrayList<>();

        ProductDataDto productDataDto1 = new ProductDataDto(1, "手机", "苹果", 1);list.add(productDataDto1);
        ProductDataDto productDataDto2 = new ProductDataDto(1, "手机", "小米", 2);list.add(productDataDto2);
        ProductDataDto productDataDto3 = new ProductDataDto(1, "手机", "华为", 3);list.add(productDataDto3);

        ProductDataDto productDataDto4 = new ProductDataDto(2, "汽车", "奔驰", 1);list.add(productDataDto4);
        ProductDataDto productDataDto5 = new ProductDataDto(2, "汽车", "宝马", 2);list.add(productDataDto5);
        ProductDataDto productDataDto6 = new ProductDataDto(2, "汽车", "奥迪", 3);list.add(productDataDto6);

        ProductDataDto productDataDto7 = new ProductDataDto(3, "公司", "移动", 1);list.add(productDataDto7);
        ProductDataDto productDataDto8 = new ProductDataDto(3, "公司", "电信", 2);list.add(productDataDto8);
        ProductDataDto productDataDto9 = new ProductDataDto(3, "公司", "联通", 3);list.add(productDataDto9);

        return list;
    }

}
