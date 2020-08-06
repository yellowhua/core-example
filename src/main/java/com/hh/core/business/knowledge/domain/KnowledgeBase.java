package com.hh.core.business.knowledge.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 训练之后的知识
 */
@Data
@Entity
@Table(name = "CM_KNOWLEDGE_BASE")
public class KnowledgeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "KNOWLEDGE_TITLE")
    private String knowledgeTitle;

    @Column(name = "KNOWLEDGE_CONTENT")
    private String knowledgeContent;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "INSERT_TIME")
    private Date insertTime;

}
