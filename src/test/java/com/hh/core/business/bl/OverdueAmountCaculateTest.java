package com.hh.core.business.bl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <pre>
 * desc：逾期金额计算测试类
 * </pre>
 *
 * @className： com.hh.core.business.OverdueAmountCaculateTest
 * @author： huanghua
 * @date: 2023/7/7
 */
@Slf4j
public class OverdueAmountCaculateTest {

    @Test
    public void testCaculate() {
        LocalDate expireDate = LocalDate.of(2023, 3, 28);
        LocalDate repayDate = LocalDate.of(2023, 7, 10);
        long day = repayDate.toEpochDay() - expireDate.toEpochDay();
        log.info("逾期天数：{}", day);
        BigDecimal princal = new BigDecimal(6531.83);
        BigDecimal overdueRate = new BigDecimal(0.2367);
        BigDecimal overdueAmount = OverdueAmountCaculate.caculate(day, princal, overdueRate);
        log.info("逾期金额：{}", overdueAmount);
    }

}
