package com.hh.core.business.snmp.repository;

import com.hh.core.business.snmp.domain.Em13cTrap;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hh on 2019/12/11.
 * 采集方式repository
 */
public interface Em13cTrapRepository extends JpaRepository<Em13cTrap, Integer> {
}
