package com.hh.core.business.ggfwwt.company;

import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
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
    public void testFindJobs() {
        try {
            // 远程的wsdl地址
            String endpoint = "http://localhost:8081/authws/services/JobService?wsdl";
            Service service = new Service();
            // 调用第一个接口
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(new QName("http://www.ylzinfo.com/xsd", "findJobs"));
            call.addParameter("creditCode", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.addParameter("rows", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.addParameter("cpage", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
//            call.addParameter("ccb022", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
//            call.addParameter("caz001", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            String result = (String) call.invoke(new Object[] { "12350822MB0573976H", "5", "1"});
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testFindJobDetail() {
        try {
            // 远程的wsdl地址
            String endpoint = "http://localhost:8081/authws/services/JobService?wsdl";
            Service service = new Service();
            // 调用第一个接口
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(new QName("http://www.ylzinfo.com/xsd", "findJobDetail"));
            call.addParameter("ccb020", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            String result = (String) call.invoke(new Object[] { "10000000000043" });
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testAddJob() {
        try {
            // 远程的wsdl地址
            String endpoint = "http://localhost:8081/authws/services/JobService?wsdl";
            Service service = new Service();
            // 调用第一个接口
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(new QName("http://www.ylzinfo.com/xsd", "addJob"));
            call.addParameter("data", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            String data = "{\n" +
                    "\t\"ccb035\": \"3000\",\n" +
                    "\t\"ccb036\": \"111111\",\n" +
                    "\t\"ccb037\": \"10\",\n" +
                    "\t\"ccb038\": \"2020-12-01\",\n" +
                    "\t\"aaa020\": \"4501240000000\",\n" +
                    "\t\"aae004\": \"哈哈\",\n" +
                    "\t\"ccb534\": \"观日路18号\",\n" +
                    "\t\"aaa020_s\": \"马山县\",\n" +
                    "\t\"creditCode\": \"12350822MB0573976H\",\n" +
                    "\t\"ccb030\": \"1000\",\n" +
                    "\t\"aac004\": \"2\",\n" +
                    "\t\"aae005\": \"18965082081\",\n" +
                    "\t\"ccb031\": \"001\",\n" +
                    "\t\"ccb033\": \"很好\",\n" +
                    "\t\"ccb034\": \"好工作\",\n" +
                    "\t\"cce511\": \"哲学研究人员\",\n" +
                    "\t\"ccb027\": \"001\",\n" +
                    "\t\"ccb028\": \"25\",\n" +
                    "\t\"ccb029\": \"50\",\n" +
                    "\t\"aac013\": \"5\",\n" +
                    "\t\"aac011\": \"62\",\n" +
                    "\t\"aae012\": \"厦门\",\n" +
                    "\t\"cce517\": \"我是clob\",\n" +
                    "\t\"cce510\": \"2010100\",\n" +
                    "\t\"ccb022\": \"很好玩玩儿\",\n" +
                    "\t\"ccb023\": \"2020-12-03\",\n" +
                    "\t\"ccb540\": \"专业没要求\"\n" +
                    "}";
            String result = (String) call.invoke(new Object[] { data });
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testUpdateJob() {
        try {
            // 远程的wsdl地址
            String endpoint = "http://localhost:8081/authws/services/JobService?wsdl";
            Service service = new Service();
            // 调用第一个接口
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(new QName("http://www.ylzinfo.com/xsd", "updateJob"));
            call.addParameter("data", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            String data = "{\n" +
                    "\t\"ccb035\": \"3000\",\n" +
                    "\t\"ccb036\": \"111111\",\n" +
                    "\t\"ccb037\": \"10\",\n" +
                    "\t\"ccb038\": \"2020-12-01\",\n" +
                    "\t\"aaa020\": \"4501240000000\",\n" +
                    "\t\"aae004\": \"哈哈\",\n" +
                    "\t\"ccb534\": \"观日路18号\",\n" +
                    "\t\"aaa020_s\": \"马山县\",\n" +
                    "\t\"creditCode\": \"12350822MB0573976H\",\n" +
                    "\t\"ccb030\": \"1000\",\n" +
                    "\t\"aac004\": \"2\",\n" +
                    "\t\"aae005\": \"18965082081\",\n" +
                    "\t\"ccb031\": \"001\",\n" +
                    "\t\"ccb033\": \"很好\",\n" +
                    "\t\"ccb034\": \"好工作\",\n" +
                    "\t\"cce511\": \"哲学研究人员\",\n" +
                    "\t\"ccb027\": \"001\",\n" +
                    "\t\"ccb028\": \"25\",\n" +
                    "\t\"ccb029\": \"50\",\n" +
                    "\t\"aac013\": \"5\",\n" +
                    "\t\"aac011\": \"62\",\n" +
                    "\t\"aae012\": \"厦门\",\n" +
                    "\t\"cce517\": \"我是clob\",\n" +
                    "\t\"caz001\": \"001\",\n" +
                    "\t\"ccb020\": \"10000000000035\",\n" +
                    "\t\"cce510\": \"2010100\",\n" +
                    "\t\"ccb022\": \"好工作\",\n" +
                    "\t\"ccb023\": \"2020-12-03\",\n" +
                    "\t\"ccb540\": \"专业没要求\"\n" +
                    "}";
            String result = (String) call.invoke(new Object[] { data });
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testDeleteJob() {
        try {
            // 远程的wsdl地址
            String endpoint = "http://localhost:8080/authws/services/JobService?wsdl";
            Service service = new Service();
            // 调用第一个接口
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(new QName("http://www.ylzinfo.com/xsd", "deleteJob"));
            call.addParameter("ccb020", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            String result = (String) call.invoke(new Object[] { "10000001021824" });
            log.info(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testLpad() {
        StringBuilder sb = new StringBuilder("111");
        while (sb.length() < 13) {
            sb.insert(0, "0");
        }
        sb.insert(0, "1");
        log.info("{}", sb);
    }

    @Test
    public void testFindSyscodes() {
        try {
            // 远程的wsdl地址
            String endpoint = "http://localhost:8080/authws/services/SyscodeService?wsdl";
            Service service = new Service();
            // 调用第一个接口
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(new QName("http://www.ylzinfo.com/xsd", "loadSyscode"));
            call.addParameter("type", XMLType.XSD_STRING, ParameterMode.IN);// 接口的参数名称
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            String AAC011 = (String) call.invoke(new Object[] { "AAC011" });
            String CCB027 = (String) call.invoke(new Object[] { "CCB027" });
            String AAC004 = (String) call.invoke(new Object[] { "AAC004" });
            String CCE601 = (String) call.invoke(new Object[] { "CCE601" });
            String CCB031 = (String) call.invoke(new Object[] { "CCB031" });
            String AAC013 = (String) call.invoke(new Object[] { "AAC013" });
            String CCE510 = (String) call.invoke(new Object[] { "CCE510" });
            String AAA020 = (String) call.invoke(new Object[] { "AAA020_QG" });
            log.info(AAC011);
            log.info(CCB027);
            log.info(AAC004);
            log.info(CCE601);
            log.info(CCB031);
            log.info(AAC013);
            log.info(CCE510);
            log.info(AAA020);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
