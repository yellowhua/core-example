package com.hh.core.business.knowledge.repository;

import com.hh.core.business.knowledge.domain.KnowledgeBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Integer> {
}
