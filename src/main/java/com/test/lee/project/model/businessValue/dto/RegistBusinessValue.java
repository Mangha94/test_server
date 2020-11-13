package com.test.lee.project.model.businessValue.dto;

import com.test.lee.project.model.businessValue.type.ValueType;
import lombok.Data;

@Data
public class RegistBusinessValue {
    /**
     * 순서
     */
    private Integer seq;

    /**
     * 이름
     */
    private String name;

    /**
     * 벨류타입
     */
    private ValueType valueType;
}
