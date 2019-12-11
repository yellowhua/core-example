package com.hh.core.jpa.repository;

import com.hh.core.jpa.domain.CollectMethod;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hh on 2019/12/11.
 * 采集方式repository
 */
public interface CollectMethodRepository extends JpaRepository<CollectMethod, Integer> {
}
