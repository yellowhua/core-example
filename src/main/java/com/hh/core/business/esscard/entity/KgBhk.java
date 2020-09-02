package com.hh.core.business.esscard.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "esc_kg_bhk")
@Data
public class KgBhk {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CREATE_TIME")
    private String createTime;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "OUT_TRADE_NO")
    private String outTradeNo;

    @Column(name = "RESULT")
    private String result;

}
