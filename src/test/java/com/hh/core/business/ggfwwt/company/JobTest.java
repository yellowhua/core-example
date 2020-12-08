package com.hh.core.business.ggfwwt.company;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

/**
 * 岗位模块webservice调试
 *
 * @author huanghua
 * @date 2020/12/8
 */
@Slf4j
public class JobTest {

    @Test
    public void testRemoteCall() {
        try {
            // 远程的wsdl地址
            String endpoint = "http://localhost:8080/authws/services/JobService?wsdl";
            Service service = new Service();
            // 调用第一个接口
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
//            call.setOperationName("authorization");// WSDL里面描述的接口名称，当这种调用不到的时候,可以使用下面的,加入命名空间名
            call.setOperationName(new QName("http://www.ylzinfo.com/xsd", "findJobs"));
            call.addParameter("creditCode", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            String xml = (String) call.invoke(new Object[] { "1" });
            log.info(xml);
            Document doc = DocumentHelper.parseText(xml);
            //获取根节点
            Element rootElement = doc.getRootElement();
            //获取根节点下的直接子节点的个数和名字
            Element element = rootElement.element("guid");
            if (null == element) {
                throw new RuntimeException("接口验证失败");
            }
            String guid = element.getText();
            log.info(guid);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
