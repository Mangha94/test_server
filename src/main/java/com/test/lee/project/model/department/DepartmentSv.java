package com.test.lee.project.model.department;

import com.test.lee.project.model.department.data.Department;
import com.test.lee.project.model.department.dto.RegistDepartment;
import com.test.lee.project.model.department.dto.UpdateDepartment;
import com.test.lee.project.model.department.searchData.DepartmentSearchData;

import java.util.List;

public interface DepartmentSv {

    /**
     * 부서를 추가한다.
     * @param registDepartment 추가 부서
     * @return 추가된 부서
     */
    Department insert(RegistDepartment registDepartment);

    /**
     * 부서를 수정한다.
     * @param updateDepartment 수정 부서
     * @return 수정된 부서
     */
    Department update(UpdateDepartment updateDepartment);

    /**
     * 부서를 삭제 한다.
     * @param id 삭제할 부서 아이디
     * @return 삭제 여부
     */
    boolean delete(String id);

    /**
     * 부서 리스트
     * @param searchData 검색 데이터
     * @return 리스트
     */
    List<Department> gets(DepartmentSearchData searchData);

    /**
     * 부서 하나를 가져온다.
     * @param id 부서 아이디
     * @return 부서
     */
    Department get(String id);

}
