package com.hh.core.business.blockchain.entity;

import lombok.Data;

import java.util.List;

/**
 * <pre>
 * 描述：速传电子仓单
 * </pre>
 *
 * @类名： com.example.demo.entity.SuchuanBill
 * @作者： huanghua
 * @创建日期: 2021/5/10 15:48
 */
@Data
public class SuchuanBill {

    private String billCode;

    private String fillTime;

    private String storageUnit;

    private String storageAddr;

    private String storageMobile;

}
