package com.test.lee.project.model.accessGroup;

import com.test.lee.project.model.accessGroup.data.AccessGroup;
import com.test.lee.project.model.accessGroup.dto.RegistAccessGroup;
import com.test.lee.project.model.accessGroup.dto.UpdateAccessGroup;
import com.test.lee.project.model.accessGroup.searchData.AccessGroupSearchData;

import java.util.List;

public interface AccessGroupSv {

    /**
     * 접근 권한 그룹을 등록한다
     * @param registAccessGroup 등록할 데이터
     * @return 등록된 데이터
     */
    AccessGroup insert(RegistAccessGroup registAccessGroup);

    /**
     * 접근 권한 그룹을 수정한다.
     * @param updateAccessGroup 수정할 데이터
     * @return 수저된 데이터
     */
    AccessGroup update(UpdateAccessGroup updateAccessGroup);

    /**
     * 접근 권한 그룹을 삭제한다.
     * @param id 삭제할 그룹 아이디
     * @return 삭제 여부
     */
    boolean delete(String id);

    /**
     * 접근 권한 그룹 리스트를 가져온다.
     * @param searchData 검색 데이터
     * @return 리스트
     */
    List<AccessGroup> gets(AccessGroupSearchData searchData);

    /**
     * 접근 권한 그룹을 가져온다.
     * @param id 가져올 그룹 아이디
     * @return 접근 권한 그룹
     */
    AccessGroup get(String id);
}
