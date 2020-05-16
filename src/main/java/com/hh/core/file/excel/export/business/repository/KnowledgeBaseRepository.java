package com.hh.core.file.excel.export.business.repository;

import com.hh.core.file.excel.export.business.domain.KnowledgeBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Integer> {
}
