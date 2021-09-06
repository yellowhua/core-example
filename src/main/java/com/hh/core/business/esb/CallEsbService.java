package com.hh.core.business.esb;

import com.hh.core.business.esb.entity.EsbParams;
import com.hh.core.business.esb.entity.EsbResult;
import com.hh.core.business.esb.enums.EsbServiceId;
import com.hh.core.business.esb.propertity.EsbPropertity;
import com.hh.core.business.esb.util.EsbUtil;
import com.ylzinfo.esb.bas.EsbResponse;
import com.ylzinfo.esb.client.XMLRequest;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallEsbService {

    private static final Logger logger = LoggerFactory.getLogger(CallEsbService.class);

    @Autowired
    private EsbPropertity esbPropertity;

    public EsbResult doAction(EsbServiceId serviceId, EsbParams params) {
        XMLRequest xmlRequest = new XMLRequest();
        //esb服务地址
        xmlRequest.setEsbUrl(esbPropertity.getEsbUrl());
        //用户名密码
        xmlRequest.setEsbUserPwd(new String[]{esbPropertity.getEsbUser(), esbPropertity.getEsbPassword()});
        String[] paramsArray = params.getParams();
        String[] valuesArray = params.getValues();
        //服务ID
        xmlRequest.setSvid(serviceId.getSid());
        //参数名称列表
        xmlRequest.setParam(paramsArray);
        //参数值列表
        xmlRequest.setParamValue(valuesArray);
        //路由
        xmlRequest.setRouter(params.getRouter());
        EsbResponse response = xmlRequest.postXmlRequest();
        //设置日志打印级别
        if ("debug".equals(esbPropertity.getEsbLogLevel())) {
            logger.debug("ESB service id: {}", serviceId);
            logger.debug("ESB result: {}", response.getResponseData());
        } else if ("info".equals(esbPropertity.getEsbLogLevel())){
            logger.debug("ESB service id: {}", serviceId);
        }

        // 解析ESB返回的报文
        EsbResult esbResult;
        try {
            esbResult = EsbUtil.parseAction(response.getResponseData());
        } catch (DocumentException e) {
            e.printStackTrace();
            esbResult = new EsbResult();
            esbResult.setResultcode(false);
            esbResult.setResultstring(e.getMessage());
        }
        return esbResult;
    }
}
