package com.hh.core.file.excel.read.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by hh on 2019/12/11.
 * 采集方式类
 */
@Data
@Entity
@Table(name = "CM_MONITOR_COLLECT_METHOD")
public class CollectMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "KPI_ID")
    private String kpiId;

    @Column(name = "COLLECT_METHOD")
    private String collectMethod;

}
