package com.hh.core.tool.snmptrap.receive;

import com.hh.core.tool.snmptrap.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.snmp4j.*;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Vector;

/**
 * Created by hh on 2019/6/24.
 * trap接收程序
 */
@Component
public class TrapReceive implements CommandResponder {

    private static final Logger logger = LogManager.getLogger(TrapReceive.class);

    /**
     * 监听trap信息
     */
    public void listenTrap() {
        try {
            init();
            logger.info("开始监听Trap信息!");
        } catch (Exception ex) {
            logger.error(ex.toString(), ex);
        }

    }

    private void init() throws IOException {
        logger.info("====== init snmp start ======");

        ThreadPool threadPool = ThreadPool.create("trapPool", Constants.THREAD_NUM);
        MultiThreadedMessageDispatcher dispatcher = new MultiThreadedMessageDispatcher(
                threadPool, new MessageDispatcherImpl());

        String localIp = InetAddress.getLocalHost().getHostAddress();
        String localAddress = "udp:" + localIp + "/" + Constants.LOCAL_PORT;
        logger.info("listening address： " + localAddress);
        Address listenAddress = GenericAddress.parse(
                System.getProperty("snmp4j.listenAddress", localAddress));

        // 对TCP与UDP协议进行处理
        TransportMapping transport;
        if (listenAddress instanceof UdpAddress) {
            transport = new DefaultUdpTransportMapping((UdpAddress) listenAddress);
        } else {
            transport = new DefaultTcpTransportMapping((TcpAddress) listenAddress);
        }

        Snmp snmp = new Snmp(dispatcher, transport);
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());

        USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(
                MPv3.createLocalEngineID()), 0);
        SecurityModels.getInstance().addSecurityModel(usm);
        snmp.listen();
        snmp.addCommandResponder(this);
        logger.info("====== init snmp end ======");
    }

    /**
     * 实现CommandResponder的processPdu方法, 用于处理传入的请求、PDU等信息，当接收到trap时，会自动进入这个方法
     * @param respEvent 监听到的时间
     */
    public void processPdu(CommandResponderEvent respEvent) {
        // 解析Response
        if (respEvent != null && respEvent.getPDU() != null) {
            logger.info("====== 接收到新的trap ======");

            //获取代理地址
            IpAddress address = (IpAddress) respEvent.getPeerAddress();
            String ip = address.toString().split("/")[0];
            logger.info("trap发送端ip:{}", ip);

            //构建trap对象
            Vector<VariableBinding> recVBs = (Vector<VariableBinding>)
                    respEvent.getPDU().getVariableBindings();
            for (int i = 0; i < recVBs.size(); i++) {
                VariableBinding recVB = recVBs.elementAt(i);
                String oid = recVB.getOid().toString();
                String variable = recVB.getVariable().toString();
                logger.info( "oid:{}, variable:{}", oid, variable);
            }
        }
    }

    public static void main(String[] args) {
        TrapReceive trapReceive = new TrapReceive();
        trapReceive.listenTrap();
    }
}
