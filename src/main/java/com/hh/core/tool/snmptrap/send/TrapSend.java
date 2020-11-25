package com.hh.core.tool.snmptrap.send;

import org.snmp4j.PDU;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TimeTicks;
import org.snmp4j.smi.VariableBinding;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hh on 2019/6/24.
 * trap发送程序
 */
public class TrapSend {

    private static void sendv2Trap(String host, String community,
                                   Map<String, String> customMap) throws IOException{
        // 初始化，建立snmp对象和target对象
        SnmpV1V2cEngine engine = new SnmpV1V2cEngine(host, community, SnmpConstants.version2c);

        // 建立PDU对象
        PDU request = Snmp4JHelper.createPDU(PDU.TRAP);

        // 1.let uptime just be system time...
        TimeTicks sysUpTime = new TimeTicks(System.currentTimeMillis() / 1000);
        request.add(new VariableBinding(SnmpConstants.sysUpTime, sysUpTime));

        // 2.define snmp trap OID
        OID trapOID = SnmpConstants.coldStart;
        request.add(new VariableBinding(SnmpConstants.snmpTrapOID, trapOID));

        // 3. define custom oid
        for (Map.Entry<String, String> entry : customMap.entrySet()) {
            request.add(new VariableBinding(new OID(entry.getKey()), new OctetString(entry.getValue())));
        }

        // 发起snmp request
        engine.sendRequest(request);

        engine.finalize();
    }

    public static void main(String[] args) throws IOException {
        String host = "10.1.3.107/162";
        String community = "public";

        Map<String,String> customMap = new HashMap<>();
        customMap.put("1.1.1.1.1.1","1");
        customMap.put("2.2.2.2.2.2","2");

        sendv2Trap(host, community, customMap);
    }

}
