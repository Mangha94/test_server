package com.test.lee.project.model.member;

import com.test.lee.project.model.businessValue.type.ValueType;

public interface MemberActionSv {

    /**
     * 벨류 값이 바뀌었다
     * @param valueName 벨류 네임
     * @param valueType 벨류 타입
     * @param isDelete 삭제여부
     */
    void changeValue(String valueName, ValueType valueType, boolean isDelete);
}
