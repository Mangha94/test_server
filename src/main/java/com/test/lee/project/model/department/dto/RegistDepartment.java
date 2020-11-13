package com.test.lee.project.model.department.dto;

import lombok.Data;

@Data
public class RegistDepartment {
    /**
     * 부서이름
     */
    private String name;

    /**
     * 부서코드
     */
    private String code;

    /**
     * 순서
     */
    private int seq;

    /**
     * 레벨
     */
    private int level;

    /**
     * 상위 부서
     */
    private String parent;
}
