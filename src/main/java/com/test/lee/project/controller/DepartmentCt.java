package com.test.lee.project.controller;

import com.test.lee.project.common.data.ApiResult;
import com.test.lee.project.model.department.DepartmentSv;
import com.test.lee.project.model.department.data.Department;
import com.test.lee.project.model.department.dto.RegistDepartment;
import com.test.lee.project.model.department.dto.UpdateDepartment;
import com.test.lee.project.model.department.searchData.DepartmentSearchData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentCt {

    final private DepartmentSv departmentSv;

    @PostMapping("/list")
    public ApiResult get(
            @RequestBody DepartmentSearchData searchData
    ){
        ApiResult result = new ApiResult();
        List<Department> list = departmentSv.gets(searchData);

        return result.success("success", list);
    }

    @GetMapping("detail/{id}")
    public ApiResult get(
            @PathVariable("id")String id
    ){
        ApiResult result = new ApiResult();

        Department department = departmentSv.get(id);
        if(department == null)
            return result.fail("해당 정보가 존재하지 않습니다.", null);
        else
            return result.success("success", department);
    }

    @PostMapping("/insert")
    public ApiResult insert(
            @RequestBody RegistDepartment registDepartment
    ){
        ApiResult result = new ApiResult();

        Department department = departmentSv.insert(registDepartment);

        if(department == null)
            return result.fail("등록에 실패했습니다.", null);
        else
            return result.success("등록되었습니다.", department);
    }

    @PostMapping("/update")
    public ApiResult update(
            @RequestBody UpdateDepartment updateDepartment
    ){
        ApiResult result = new ApiResult();

        Department department = departmentSv.update(updateDepartment);

        if(department == null)
            return result.fail("수정에 실패했습니다.", null);
        else
            return result.success("수정되었습니다.", department);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult delete(
            @PathVariable("id")String id
    ){
        ApiResult result = new ApiResult();

        if(departmentSv.delete(id))
            result.success("삭제되었습니다.");
        else
            result.fail("존재하지 않는 데이터 입니다.");

        return result;
    }
}
