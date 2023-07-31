package com.hh.core.business.bl;

import com.hh.core.business.lyrlzyw.ca.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * <pre>
 * desc：企查查接口调用
 * </pre>
 *
 * @className： com.hh.core.business.bl.QccInvokeInfo
 * @author： huanghua
 * @date: 2023/7/21
 */
@Component
@Slf4j
public class QccInvokeUtil {

    @Autowired
    private RestTemplate restTemplate;

    public static void getInfo() {
        String key = "3d796736fe4e11eb990b0c42a106ce72";
        String timespan = getSecondTimestamp(new Date());
        String secretKey = "HFC9WIIS3VRZA7VGEC8T79EX819WE2TL";
        String tokenValue = key + timespan + secretKey;
        String token = MD5Util.MD5(tokenValue).toUpperCase();
        log.info("timespan:{}", timespan);
        log.info("token:{}", token);
    }

    public static String getSecondTimestamp(Date date){
        if (null == date) {
            return StringUtils.EMPTY;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return timestamp.substring(0,length - 3);
        } else {
            return StringUtils.EMPTY;
        }
    }

    public static void main(String[] args) {
        getInfo();
    }

}
