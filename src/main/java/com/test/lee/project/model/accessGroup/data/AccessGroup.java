package com.test.lee.project.model.accessGroup.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AccessGroup {
    @Id
    private String id;

    /**
     * 그룹이름
     */
    private String name;

    /**
     * 그룹설명
     */
    private String memo;

    /**
     * 순서
     */
    private int seq;

    private AccessData accessData;
}
