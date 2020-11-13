package com.test.lee.project.model.accessGroup.dto;

import com.test.lee.project.model.accessGroup.data.AccessData;
import lombok.Data;

@Data
public class RegistAccessGroup {
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
