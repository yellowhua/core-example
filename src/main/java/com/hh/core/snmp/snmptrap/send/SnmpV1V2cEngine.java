package com.hh.core.snmp.snmptrap.send;

import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OctetString;

import java.io.IOException;

/**
 * Created by hh on 2018/6/4.
 */
public class SnmpV1V2cEngine {

    private Snmp snmp;
    private Target target;

    private Address address;
    private OctetString community = new OctetString("public");
    private int version = SnmpConstants.version2c;

    private int retries = 1;
    private int timeout = 1000;
    private int maxSizeResponsePDU = 65535;

    public SnmpV1V2cEngine(String address, String community) {
        this(address, community,null);
    }

    public SnmpV1V2cEngine(String address, String community, Integer version) {
        super();
        this.address = Snmp4JHelper.getAddress(address);
        this.community = Snmp4JHelper.createOctetString(community);
        if( null != version)
            this.version = version;
        try {
            snmpInitial();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    /////////////////////////////////////////////////////////////////

    private void snmpInitial() throws IOException {
        // 建立Snmp对象
        this.snmp = Snmp4JHelper.createSnmpSession(address);

        // 建立Target对象
        this.target = Snmp4JHelper.createTarget(address, community);

        target.setVersion(version);
        target.setRetries(retries);
        target.setTimeout(timeout);
        target.setMaxSizeRequestPDU(maxSizeResponsePDU);

        snmp.listen();
    }

    public void close() {
        if (null != snmp) {
            try {
                snmp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            snmp = null;
        }
    }

    public void finalize() {
        close();
    }

    public ResponseEvent sendRequest(PDU request)
            throws IOException {
        return snmp.send(request, this.target);
    }
}
