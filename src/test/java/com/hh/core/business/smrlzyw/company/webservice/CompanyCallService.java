package com.hh.core.business.smrlzyw.company.webservice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hh.core.business.smrlzyw.company.webservice.wsdl.Interfaceservice;
import com.hh.core.business.smrlzyw.company.webservice.wsdl.InterfaceserviceService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

/**
 * @author huanghua
 * @date 2020/11/17
 */
@Slf4j
public class CompanyCallService {

    @Test
    public void testGetNameAndCard() throws DocumentException {
        InterfaceserviceService interfaceserviceService = new InterfaceserviceService();
        Interfaceservice soap = interfaceserviceService.getInterfaceservicePort();
        String guidXml = soap.authorization("smsrlzyh_smsrlz_X3HW", "bkzt~wC1");
        log.info(guidXml);
        Document doc = DocumentHelper.parseText(guidXml);
        //获取根节点
        Element rootElement = doc.getRootElement();

        //获取根节点下的直接子节点的个数和名字
        Element element = rootElement.element("guid");
        if (null == element) {
            throw new RuntimeException("接口验证失败");
        }
        String guid = element.getText();
        log.info(guid);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("businessCode", "222");
        jsonObject.put("enterp", "永安市莲花山天然香料有限公司1");
        String str = soap.microServerGxjkQuery(guid, "APPLYCODE144", jsonObject.toJSONString());
        log.info(str);

        JSONObject data = JSONObject.parseObject(JSONObject.parseObject(str).getString("data"));
        JSONArray result = data.getJSONArray("result");

        if (null == result || result.size() == 0) {
            throw new RuntimeException("当前企业全称未找到相关法人信息，请核对");
        }

        JSONObject info = result.getJSONObject(0);
        String name = info.getString("name");
        String identificationcode = info.getString("identificationcode");
    }

    @Test
    public void testGetCompanyBaseInfo() throws DocumentException {
        InterfaceserviceService interfaceserviceService = new InterfaceserviceService();
        Interfaceservice soap = interfaceserviceService.getInterfaceservicePort();
        String guidXml = soap.authorization("smsrlzyh_smsrlz_7pEq", "L*12luYc");
        log.info(guidXml);
        Document doc = DocumentHelper.parseText(guidXml);
        //获取根节点
        Element rootElement = doc.getRootElement();

        //获取根节点下的直接子节点的个数和名字
        Element element = rootElement.element("guid");
        if (null == element) {
            throw new RuntimeException("接口验证失败");
        }
        String guid = element.getText();
        log.info(guid);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("businessCode", "222");
        jsonObject.put("enterp", "永安市莲花山天然香料有限公司");
        String str = soap.microServerGxjkQuery(guid, "APPLYCODE143", jsonObject.toJSONString());
        log.info(str);

        JSONObject data = JSONObject.parseObject(JSONObject.parseObject(str).getString("data"));
        if (null == data || data.size() == 0) {
            throw new RuntimeException("当前企业全称未找到相关法人信息，请核对");
        }

        JSONObject baseInfo = data.getJSONObject("result").getJSONObject("nts_data").getJSONObject("basicinfo");
        String name = baseInfo.getString("name");
        String identificationcode = baseInfo.getString("identificationcode");
    }

}
