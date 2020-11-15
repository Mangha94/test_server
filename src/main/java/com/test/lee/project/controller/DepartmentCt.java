package com.test.lee.project.controller;

import com.test.lee.project.common.data.ApiResult;
import com.test.lee.project.common.exception.DepartmentException;
import com.test.lee.project.model.department.DepartmentSv;
import com.test.lee.project.model.department.data.Department;
import com.test.lee.project.model.department.dto.DepartmentContainer;
import com.test.lee.project.model.department.dto.RegistDepartment;
import com.test.lee.project.model.department.dto.UpdateDepartment;
import com.test.lee.project.model.department.searchData.DepartmentSearchData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        int maxLevel = list.stream().mapToInt(Department :: getLevel).max().orElse(0) + 1;

        List<Department> temp = list.stream().filter(r -> r.getLevel() == 0).collect(Collectors.toList());

        List<DepartmentContainer> set = new ArrayList<>();

        temp.forEach(r -> {
            set.add(new DepartmentContainer(r, list, maxLevel));
        });

        return result.success("success", set);
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

        Department department = null;
        try {
            department = departmentSv.insert(registDepartment);

            return result.success("등록되었습니다.", department);
        } catch (DepartmentException e) {
            return result.fail(e.getMessage(), null);
        }
    }

    @PostMapping("/update")
    public ApiResult update(
            @RequestBody UpdateDepartment updateDepartment
    ){
        ApiResult result = new ApiResult();

        Department department = null;
        try {
            department = departmentSv.update(updateDepartment);

            return result.success("수정되었습니다.", department);
        } catch (DepartmentException e) {
            return result.fail(e.getMessage(), null);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ApiResult delete(
            @PathVariable("id")String id
    ){
        ApiResult result = new ApiResult();

        try {
            if(departmentSv.delete(id))
                result.success("삭제되었습니다.");
        }catch (DepartmentException e){
            result.fail(e.getMessage());
        }

        return result;
    }
}
