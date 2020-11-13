package com.test.lee.project.model.businessValue.data;

import com.test.lee.project.model.businessValue.dto.RegistBusinessValue;
import com.test.lee.project.model.businessValue.type.ValueType;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BusinessValue {

    @Id
    private String id;

    /**
     * 순서
     */
    private int seq;

    /**
     * 이름
     */
    private String name;

    /**
     * 벨류타입
     */
    private ValueType valueType;

    public BusinessValue() {
    }

    public BusinessValue(RegistBusinessValue registBusinessValue) {
        this.seq = registBusinessValue.getSeq();
        this.name = registBusinessValue.getName();
        this.valueType = registBusinessValue.getValueType();
    }

    public BusinessValue update(BusinessValue businessValue, RegistBusinessValue registBusinessValue) {

        if(registBusinessValue.getSeq() != null){
            businessValue.setSeq(registBusinessValue.getSeq());
        }

        if(registBusinessValue.getName() != null){
            businessValue.setName(registBusinessValue.getName());
        }

        if(registBusinessValue.getName() != null){
            businessValue.setName(registBusinessValue.getName());
        }

        if(registBusinessValue.getValueType() != null){
            businessValue.setValueType(registBusinessValue.getValueType());
        }

        return businessValue;
    }
}
