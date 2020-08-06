package com.hh;

import com.hh.core.interfacer.resttemplate.jsonparam.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreExampleApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test() {
		String url = "http://localhost:8080/lyrlzyw/ggfwsso-client/sso.shtml";
		Map<String, Object> map = new HashMap<>();
		map.put("token", "123");

		// 调用接口
		HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(map, Constants.jsonHeader());
		Map<String, Object> result = restTemplate.postForObject(url, httpEntity, Map.class);

		System.out.println(result);

	}

}
