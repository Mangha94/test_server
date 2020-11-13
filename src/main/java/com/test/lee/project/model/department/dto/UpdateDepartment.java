package com.test.lee.project.model.department.dto;

import lombok.Data;

@Data
public class UpdateDepartment {

    /**
     * 부서 아이디
     */
    private String id;

    /**
     * 부서이름
     */
    private String name;

    /**
     * 부서코드
     */
    private String code;
}
