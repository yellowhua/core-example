package com.hh.core.business.lyrlzyw.ca.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * 
 * @description: 重写spring的PropertyPlaceholderConfigurer，并改用该类加载配置文件并获取配置信息
 * @date: 2015-6-30 下午6:26:07
 * @author: Xu Yajie
 */
public class PropertyConfigUtil extends PropertyPlaceholderConfigurer {

	private static Properties properties = new Properties();

	/**
	 * 
	 * @param beanFactory
	 * @param props
	 * @throws BeansException
	 * @description: 父类的该方法将配置信息提交给beanFactory，在之前解密，并初始化properties
	 * @date: 2015-6-30 下午7:04:16
	 * @author： Xu Yajie
	 */
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		for (Object propertyName : props.keySet()) {
			String prop = props.getProperty((String) propertyName);
			properties.put(propertyName, prop);
		}
		super.processProperties(beanFactory, properties);
	}

	/**
	 * 
	 * @param key
	 * @return
	 * @description: 读取配置文件信息
	 * @date: 2015-6-30 下午7:06:30
	 * @author： Xu Yajie
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

}