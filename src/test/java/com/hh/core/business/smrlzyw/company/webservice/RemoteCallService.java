package com.hh.core.business.smrlzyw.company.webservice;

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
 * @author huanghua
 * @date 2020/11/17
 */
@Slf4j
public class RemoteCallService {

    @Test
    public void testRemoteCall() {
        try {
            // 远程的wsdl地址
            String endpoint = "http://202.109.226.172:8182/Convergence/webservice/interfaceservice?wsdl";
            Service service = new Service();
            // 调用第一个接口
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
//            call.setOperationName("authorization");// WSDL里面描述的接口名称，当这种调用不到的时候,可以使用下面的,加入命名空间名
            call.setOperationName(new QName("http://tempuri.org/", "authorization"));
            call.addParameter("userid", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.addParameter("password", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            String guidXml = (String) call.invoke(new Object[] { "smsrlzyh_smsrlz_7pEq", "L*12luYc" });
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

            // 调用第二个接口
            Call call2 = (Call) service.createCall();
            call2.setTargetEndpointAddress(endpoint);
            call2.setOperationName(new QName("http://tempuri.org/", "microServerGxjkQuery"));
            call2.addParameter("guid", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call2.addParameter("applyCode", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call2.addParameter("queryJson", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call2.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("businessCode", "222");
            jsonObject.put("enterp", "永安市莲花山天然香料有限公司");
            String result = (String) call2.invoke(new Object[] { guid, "APPLYCODE143", jsonObject.toJSONString()});
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
