package com.hh;

import com.hh.core.interfacer.resttemplate.formparam.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CoreExampleApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testLogin() {
		String loginUrl = "http://192.168.44.89:8081/smrlzyw-company/login";
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("username", "com001");
		map.add("password", "111111");
		map.add("verifyCode", "9833");
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, Constants.formHeader());
		ResponseEntity<String> response = restTemplate.exchange(loginUrl, HttpMethod.POST, httpEntity, String.class);
		String resultString = response.getBody();
		HttpHeaders responseHeaders = response.getHeaders();
		List<String> cookies = responseHeaders.get("Set-Cookie");
		if (CollectionUtils.isEmpty(cookies)) {
			throw new RuntimeException("没有cookie");
		}
	}

	@Test
	public void testQuery() {
		String url = "http://192.168.44.89:8081/smrlzyw-company/company/baseInfo";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Cookie", "JSESSIONID=5cc889a1-2bd1-4287-84c2-c62aef05f739");
		HttpEntity<String> httpEntity2 = new HttpEntity<>(null, requestHeaders);
		ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity2, String.class);
		log.info("{}", resEntity);
	}

	@Test
	public void testFanruan() {
		String url = "http://localhost:8075/WebReport/ReportServer?reportlet=CreditSummary.cpt";
		String resEntity = restTemplate.getForObject(url, String.class);
		log.info("{}", resEntity);

		// 截取sessionId
		String sessionId = StringUtils.substringBefore(StringUtils.substringAfter(resEntity, "sessionID="), "\"");
		log.info("{}", sessionId);

		// 下载excel
		String downloadUrl = "http://localhost:8075/WebReport/ReportServer?op=export&format=excel&extype=simple&sessionID=" + sessionId;
		byte[] bytes = restTemplate.getForObject(downloadUrl, byte[].class);
		File file = new File("D:\\xyhh\\Desktop\\test.xlsx");
		try (OutputStream out = new FileOutputStream(file)) {
			out.write(bytes);
		} catch (IOException e) {
			log.error("文件下载失败！" + e.getMessage());
			e.printStackTrace();
		}

	}

}
