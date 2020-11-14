package com.hh.core.business.esb.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh.core.business.esb.entity.EsbResult;
import org.dom4j.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By zheng kaifa
 * Data: 2019/5/17
 * Time: 14:36
 */
public class EsbUtil {

    private static final String XPATH_TO_ROW = "/soap:Envelope/soap:Body/out:business/resultset[@name=\"retrieve\"]/row";
    private static final String XPATH_TO_RESULTSET = "/soap:Envelope/soap:Body/out:business/resultset[@name=\"retrieve\"]";
    private static final String XPATH_TO_INFORMATION = "/soap:Envelope/soap:Body/out:business/result[@information]";
    private static final String XPATH_TO_ERROR = "/soap:Envelope/soap:Body/soap:Fault/faultstring/error";
    private static final String XPATH_TO_ROWCOUNT = "/soap:Envelope/soap:Body/out:business/result[@rowCount]";
    private static final String XPATH_TO_PDFB64 = "/soap:Envelope/soap:Body/out:business/result[@pdfb64]";

    /**
     * YLZ ESB里按条件exp选择到Row的XPATH
     *
     * @param exp 选择条件
     * @return /soap:Envelope/soap:Body/out:business/resultset[@name="retrieve"]/row[exp]
     */
    public static String XPATH_TO_ROW_WITH(String exp) {
        return XPATH_TO_ROW + "[" + exp + "]";
    }

    /**
     * 解析ESB返回的XML
     *
     * @param datastr ESB返回的报文
     */
    public static EsbResult parseAction(String datastr) throws DocumentException {
        if (!datastr.startsWith("<?xml")) {
            EsbResult result = parseJson(datastr);
            return result;
        }
        EsbResult result = parseXml(datastr);
        return result;

    }

    private static EsbResult parseXml(String xml) throws DocumentException {
        EsbResult result = new EsbResult();
        // 初始化
        result.setTotal("0");
        result.setContent(new ArrayList<Map<String, String>>());
        result.getContent().add(new HashMap<String, String>());

        List<?> rows = null;
        Element element = null;

        /*
         * 1、判定是否有错误信息
         */
        element = queryElement(xml, XPATH_TO_ERROR);
        if (element != null) {
            result.setResultcode(false);
            result.setResultstring(element.attributeValue("msg"));
            return result;
        }
        /*
         * 2、判定是否有提示信息
         */
        element = queryElement(xml, XPATH_TO_INFORMATION);
        if (element != null) {
            String information = element.attributeValue("information");
            if ("OK".equals(information)) {
                result.setResultcode(true);
            } else {
                result.setResultcode(false);
            }
            result.setResultstring(information);
        }
        /*
         * 3、解析结果集
         */
        element = queryElement(xml, XPATH_TO_RESULTSET);
        if (element != null) {
            result.setResultcode(true);
            List<Map<String, String>> content = new ArrayList<Map<String, String>>();
            rows = element.selectNodes("row");
            for (Object obj : rows) {
                element = (Element) obj;
                Map<String, String> json = new HashMap<String, String>();
                List<Attribute> listAttr = element.attributes();
                for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
                    String name = attr.getName();// 属性名称
                    String value = attr.getValue();// 属性的值
                    json.put(name, value);
                }
                content.add(json);
            }
            result.setContent(content);
        }
        /*
         * 4、解析总记录数
         */
        element = queryElement(xml, XPATH_TO_ROWCOUNT);
        if (element != null) {
            result.setTotal(element.attributeValue("rowCount"));
        }
        /*
         * 5、解析pdfb64
         */
        element = queryElement(xml, XPATH_TO_PDFB64);
        if (element != null) {
            result.setPdfb64(element.attributeValue("pdfb64"));
        }

        /*
         * content长度为0时加一个空map
         */

        if (result.getContent().size() == 0) {
            result.getContent().add(new HashMap<String, String>());
        }
        return result;
    }

    /**
     * 解析json数据
     *
     * @param json
     * @return
     */
    private static EsbResult parseJson(String json) {
        EsbResult result = new EsbResult();
        ObjectMapper mapper = new ObjectMapper();
        Map resultmap;
        try {
            resultmap = mapper.readValue(json, Map.class);
            String code = (String) resultmap.get("code");
            String message = (String) resultmap.get("message");
            if ("".equals(resultmap.get("data")) || resultmap.get("data") == null) {
                result.setContent(new ArrayList<Map<String, String>>());
            } else {
                result.setContent((List<Map<String, String>>) resultmap.get("data"));
            }
            if (result.getContent() == null) {
                result.setContent(new ArrayList<Map<String, String>>());
            }
            result.setResultcode("0".equals(code) ? true : false);
            result.setResultstring(message);
            result.setTotal("" + resultmap.get("total"));
        } catch (Exception e) {
            result.setResultcode(false);
            result.setResultstring("json数据解析出错");

        }
        if (result.getContent().size() == 0 || null == result.getContent().get(0)) {
            result.getContent().clear();
            result.getContent().add(new HashMap<String, String>());
        }
        return result;
    }

    /**
     * 根据XPath查找ESB返回值对应的element
     *
     * @param xml   ESB服务返回值
     * @param xpath 查找的XPath
     * @return 查找的element，如果esb调用出错或者xpath没有配置好，返回null
     * @throws DocumentException
     */
    private static Element queryElement(String xml, String xpath) throws DocumentException {
        Element element = null;
        Map<String, String> map = new HashMap<String, String>();
        if (xml.indexOf("http://www.molss.gov.cn/") > 0) {
            map.put("out", "http://www.molss.gov.cn/");
        } else if (xml.indexOf("http://www.ylzinfo.com/") > 0) {
            map.put("out", "http://www.ylzinfo.com/");
        }
        map.put("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        Document doc = DocumentHelper.parseText(xml);
        XPath xPath = DocumentHelper.createXPath(xpath);
        xPath.setNamespaceURIs(map);
        element = (Element) xPath.selectSingleNode(doc);
        return element;
    }
}
