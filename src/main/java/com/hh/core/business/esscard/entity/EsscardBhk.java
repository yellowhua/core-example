package com.hh.core.business.esscard.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "esc_phone_recharge_result_bhk")
@Data
public class EsscardBhk {

    @Id
    @Column(name = "RECHARGE_ID")
    private String rechargeId;

    @Column(name = "OUT_TRADE_NO")
    private String outTradeNo;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "MONEY")
    private String money;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SZ_RTN_CODE")
    private String szRtnCode;

    @Column(name = "TIME_STAMP")
    private String timeStamp;

    @Column(name = "ACCOUNT_ID")
    private String accountId;

    @Column(name = "VOLUME_ID")
    private String volumeId;

    @Column(name = "CREATE_TIME")
    private String createTime;

}
