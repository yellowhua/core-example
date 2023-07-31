package com.hh.core.business.bl;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <pre>
 * desc：逾期金额计算
 * </pre>
 *
 * @className： com.hh.core.business.bl.OverdueAmountCaculate
 * @author： huanghua
 * @date: 2023/7/7
 */
@Slf4j
public class OverdueAmountCaculate {

    public static BigDecimal caculate(Long day, BigDecimal princal, BigDecimal overdueRate) {
        BigDecimal dayBig = new BigDecimal(day);
        BigDecimal yearRate = new BigDecimal(360);
        BigDecimal overdueAmount = princal.multiply(dayBig).multiply(overdueRate).divide(yearRate,2);
        log.info("逾期金额：{}", overdueAmount);
        return overdueAmount;
    }

}
