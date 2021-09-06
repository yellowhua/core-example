package com.hh.core.business.lyrlzyw.ca.util;

/**
 * @author ChenDang
 * @date 2019/7/8 0008
 */
public interface UrlConfig {

    interface Support{
        /**
         * 获取证书的绑定信息
         */
        String KEY_BIND = "OperationalSupport/ws/key/keyBind.sjson";

        /**
         * 单位信息查询
         */
        String UNIT_INFO = "OperationalSupport/ws/unitInfo.sjson";

        /**
         * 是否注册过
         */
        String IS_EXIST_UNIT = "OperationalSupport/ws/isExistUnit.sjson";

        /**
         * 单位首次注册保存
         */
        String UNIT_REGISTER = "OperationalSupport/ws/register.sjson";

        /**
         * 发送短信
         */
        String SEND_CHECK_CODE = "OperationalSupport/ws/sendSmsCode.sjson";
    }

    /**
     * E点通url
     */
    interface EDT{
        String API_LOGIN = "fjedt/apiLogin.shtml";
    }

}
