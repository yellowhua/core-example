package com.hh.core.file.excel.export.Repository;

import com.hh.core.file.excel.export.domain.KnowledgeBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Integer> {
}
