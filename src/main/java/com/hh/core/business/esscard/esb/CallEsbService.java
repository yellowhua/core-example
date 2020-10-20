package com.hh.core.business.esscard.esb;

import com.hh.core.business.esscard.esb.entity.EsbParams;
import com.hh.core.business.esscard.esb.entity.EsbResult;
import com.hh.core.business.esscard.esb.enums.EsbServiceId;
import com.hh.core.business.esscard.esb.util.EsbUtil;
import com.ylzinfo.esb.bas.EsbResponse;
import com.ylzinfo.esb.client.XMLRequest;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CallEsbService {

    private static Logger logger = LoggerFactory.getLogger(CallEsbService.class);

    @Value("${esb.url}")
    private String url;
    
    @Value("${esb.password}")
    private String password;
    
    @Value("${esb.user}")
    private String username;
    
    @Value("${esb.log-level}")
    private String logLevel;

    public EsbResult doAction(EsbServiceId serviceId, EsbParams params) {
        /**
         * 1、初始化ESB请求体
         */
        XMLRequest xmlRequest = new XMLRequest();
        xmlRequest.setEsbUrl(url);//esb服务地址
        xmlRequest.setEsbUserPwd(new String[]{username, password});//用户名密码
        /*
         * 2、请求体具体入参设置
         */
//        params.add("termid", "微信号");
        String[] paramsArray = params.getParams();
        String[] valuesArray = params.getValues();
        xmlRequest.setSvid(serviceId.getSid());//服务ID
        xmlRequest.setParam(paramsArray);//参数名称列表
        xmlRequest.setParamValue(valuesArray);//参数值列表
        xmlRequest.setRouter(params.getRouter());//路由
        EsbResponse response = xmlRequest.postXmlRequest();
//        if (ls_key != null && !"".equals(ls_key)) {
//            response = xmlRequest.postXmlRequest(ls_key);
//        } else {
//            
//        }
        //设置日志打印级别
        if ("debug".equals(logLevel)) {
            logger.debug("ESB service id: {}", serviceId);
            logger.debug("ESB result: {}", response.getResponseData());
        }else if("info".equals(logLevel)){
            logger.debug("ESB service id: {}", serviceId);
        }
        EsbResult esbREsult = null;
        /*
         * 3、 解析ESB返回的报文
         */
        try {
            esbREsult = EsbUtil.parseAction(response.getResponseData());
        } catch (DocumentException e) {
            e.printStackTrace();
            esbREsult = new EsbResult();
            esbREsult.setResultcode(false);
            esbREsult.setResultstring("系统内部异常");
        }
        return esbREsult;
    }
}
