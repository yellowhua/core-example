package com.hh.core.business.excel.export.repository;

import com.hh.core.business.excel.export.domain.KnowledgeBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Integer> {
}
