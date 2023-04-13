package com.hh.core.tool.ureport2;

import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <pre>
 * 描述：初始化servlet、内置数据源
 * </pre>
 *
 * @类名： com.hh.core.tool.ureport2.UReportConfig
 * @作者： huanghua
 * @创建日期: 2022/1/17 17:09
 */
@Configuration
@ImportResource("classpath:ureport-console-context.xml")
@Slf4j
public class UReportConfig implements BuildinDatasource {

    @Resource
    private DataSource dataSource;

    /**
     * 注册servlet组件
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean registrationBean() {
        return new ServletRegistrationBean(new UReportServlet(), "/ureport/*");

    }

    @Override
    public String name() {
        return "myDataSource";
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("uReport 数据源 获取连接失败！-{}", e.getMessage());
        }
        return null;
    }
}
