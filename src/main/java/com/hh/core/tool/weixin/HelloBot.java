package com.hh.core.tool.weixin;

import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloBot extends WeChatBot {

    private static final Logger log = LogManager.getLogger(HelloBot.class);

    public HelloBot(Config config) {
        super(config);
    }

    @Bind(msgType = MsgType.ALL)
    public void handleText(WeChatMessage message) {
        if (StringUtils.isNotEmpty(message.getName())) {
            log.info("接收到 [{}] 的消息: {}", message.getName(), message.getText());
            //this.sendMsg(message.getFromUserName(), "自动回复: " + message.getText());
        }
    }

    public static void main(String[] args) {
        new HelloBot(Config.me().autoLogin(true).showTerminal(true)).start();
    }
}
