package com.hh.core.tool.merge;

import com.hh.core.tool.merge.data.ProductData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MergeUtilTest {

    @Test
    public void testMerge() {
        List<ProductData> list = createData();
        List<ProductData> result = MergeUtil.mergeData(list);
        for (ProductData data : result) {
            System.out.println(data);
        }
    }

    private List<ProductData> createData() {
        List<ProductData> list = new ArrayList<>();

        ProductData productData1 = new ProductData(1, "手机", "苹果", 1);list.add(productData1);
        ProductData productData2 = new ProductData(1, "手机", "小米", 2);list.add(productData2);
        ProductData productData3 = new ProductData(1, "手机", "华为", 3);list.add(productData3);

        ProductData productData4 = new ProductData(2, "汽车", "奔驰", 1);list.add(productData4);
        ProductData productData5 = new ProductData(2, "汽车", "宝马", 2);list.add(productData5);
        ProductData productData6 = new ProductData(2, "汽车", "奥迪", 3);list.add(productData6);

        ProductData productData7 = new ProductData(3, "公司", "移动", 1);list.add(productData7);
        ProductData productData8 = new ProductData(3, "公司", "电信", 2);list.add(productData8);
        ProductData productData9 = new ProductData(3, "公司", "联通", 3);list.add(productData9);

        return list;
    }

}
