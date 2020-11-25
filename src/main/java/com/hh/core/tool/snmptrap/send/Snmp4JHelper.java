package com.hh.core.tool.snmptrap.send;

import org.snmp4j.*;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.AbstractTransportMapping;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;

/**
 * Created by hh on 2018/6/4.
 */
public class Snmp4JHelper {

    public static Snmp createSnmpSession(Address address) throws IOException {
        // new TransportMapping object
        AbstractTransportMapping transport;
        if (address instanceof TcpAddress) {
            transport = new DefaultTcpTransportMapping();
        }
        else {
            transport = new DefaultUdpTransportMapping();
        }

        // new Snmp object
        return new Snmp(transport);
    }

    public static Target createTarget(Address address, OctetString community) {
        CommunityTarget target = new CommunityTarget();

        target.setAddress(address);
        target.setCommunity(community);
        return target;

    }

    public static PDU createPDU(int pduType) {
        PDU request;

        if (pduType == PDU.V1TRAP) {
            request = new PDUv1();
        }
        else {
            request = new PDU();
        }

        request.setType(pduType);
        return request;
    }

    public static OctetString createOctetString(String s) {
        OctetString octetString;
        if (s.startsWith("0x")) {
            octetString = OctetString.fromHexString(s.substring(2), ':');
        }
        else {
            octetString = new OctetString(s);
        }
        return octetString;
    }


    public static Address getAddress(String transportAddress) {
        // 如果有冒号存在,就是"udp:xxx.xxx.xxx.xxx"的格式,分离出协议和地址
        int colon = transportAddress.indexOf(':');
        String transprotocol = "udp";
        if (colon > 0) {
            transprotocol = transportAddress.substring(0, colon);
            transportAddress = transportAddress.substring(colon+1);
        }

        // append default port follow end of transportAddress
        if (transportAddress.indexOf('/') < 0) {
            transportAddress += "/161";
        }

        if (transprotocol.equalsIgnoreCase("udp")) {
            return new UdpAddress(transportAddress);
        }
        else if (transprotocol.equalsIgnoreCase("tcp")) {
            return new TcpAddress(transportAddress);
        }
        throw new IllegalArgumentException("Unknown transprotocol "+transprotocol);
    }
}
