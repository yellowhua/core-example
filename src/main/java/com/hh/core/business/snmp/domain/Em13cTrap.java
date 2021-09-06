package com.hh.core.business.snmp.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by hh on 2019/12/19.
 * trap报文表
 */
@Data
@Entity
@Table(name = "CM_MIB_EM13C_TRAP_20191216")
public class Em13cTrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "IP_OID")
    private String ipOid;

    @Column(name = "DBINSTANCE_OID")
    private String dbinstanceOid;

}
