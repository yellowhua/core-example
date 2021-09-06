package com.hh.core.business.esscard.repository;

import com.hh.core.business.esscard.entity.EsscardBhk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsscardBhkRepository extends JpaRepository<EsscardBhk, String> {
}
