package com.test.lee.project.model.businessValue;

import com.test.lee.project.model.businessValue.data.BusinessValue;
import com.test.lee.project.model.businessValue.dto.RegistBusinessValue;
import com.test.lee.project.model.businessValue.searchData.ValueSearchData;

import java.util.List;

public interface BusinessValueSv {

    /**
     * 벨류 등록
     * @param registBusinessValue 등록 데이터
     * @return 등록된 데이터
     */
    BusinessValue insert(RegistBusinessValue registBusinessValue);

    /**
     * 벨류 수정
     * @param id 아이디
     * @param registBusinessValue 수정 데이터
     * @return 수정된 데이터
     */
    BusinessValue update(String id, RegistBusinessValue registBusinessValue);

    /**
     * 벨류 삭제
     * @param id 아이디
     * @return 삭제 여부
     */
    boolean delete(String id);

    /**
     * 벨류 리스트
     * @param searchData 검색데이터
     * @return 리스트
     */
    List<BusinessValue> gets(ValueSearchData searchData);

    /**
     * 벨류 하나를 가져온다
     * @param id 벨류 아이디
     * @return 벨류
     */
    BusinessValue get(String id);
}
