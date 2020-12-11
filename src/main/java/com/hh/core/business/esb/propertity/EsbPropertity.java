package com.hh.core.business.esb.propertity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author huanghua
 * @date 2020/10/20
 * esb配置信息
 * 备注：不同地市esb配置信息不一样时只要在配置文件中加上该地市的esb配置信息，然后改一下本类中prefix的值即可
 */
@Data
@Component
@ConfigurationProperties(prefix = "fj-ggfwwt")
public class EsbPropertity {

    /**
     * esb地址
     */
    private String esbUrl;

    /**
     * esb用户
     */
    private String esbUser;

    /**
     * esb密码
     */
    private String esbPassword;

    /**
     * esb日志级别
     */
    private String esbLogLevel;
}
