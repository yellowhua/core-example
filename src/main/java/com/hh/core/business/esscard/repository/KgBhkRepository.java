package com.hh.core.business.esscard.repository;

import com.hh.core.business.esscard.entity.KgBhk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KgBhkRepository extends JpaRepository<KgBhk, String> {
}
