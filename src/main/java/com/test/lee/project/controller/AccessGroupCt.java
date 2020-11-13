package com.test.lee.project.controller;

import com.test.lee.project.common.data.ApiResult;
import com.test.lee.project.model.accessGroup.AccessGroupSv;
import com.test.lee.project.model.accessGroup.data.AccessGroup;
import com.test.lee.project.model.accessGroup.dto.RegistAccessGroup;
import com.test.lee.project.model.accessGroup.dto.UpdateAccessGroup;
import com.test.lee.project.model.accessGroup.searchData.AccessGroupSearchData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accessGroup")
public class AccessGroupCt {

    final private AccessGroupSv accessGroupSv;

    @PostMapping("/list")
    public ApiResult get(
            @RequestBody AccessGroupSearchData searchData
    ){
        ApiResult result = new ApiResult();
        List<AccessGroup> list = accessGroupSv.gets(searchData);

        if(list.size() > 0)
            return result.success("success", list);
        else
            return result.fail("등록된 정보가 없습니다.", null);
    }

    @GetMapping("/detail/{id}")
    public ApiResult get(
            @PathVariable("id")String id
    ){
        ApiResult result = new ApiResult();

        AccessGroup accessGroup = accessGroupSv.get(id);

        if(accessGroup == null)
            return result.fail("해당 데이터가 존재하지 않습니다", null);
        else
            return result.success("success.", accessGroup);
    }

    @PostMapping("/insert")
    public ApiResult insert(
            @RequestBody RegistAccessGroup registAccessGroup
    ){
        ApiResult result = new ApiResult();

        AccessGroup accessGroup = accessGroupSv.insert(registAccessGroup);

        if(accessGroup == null)
            return result.fail("등록에 실패했습니다.", null);
        else
            return result.success("등록 되었습니다.", accessGroup);
    }

    @PostMapping("/update")
    public ApiResult update(
            @RequestBody UpdateAccessGroup updateAccessGroup
    ){
        ApiResult result = new ApiResult();

        AccessGroup accessGroup = accessGroupSv.update(updateAccessGroup);

        if(accessGroup == null)
            return result.fail("수정에 실패했습니다.", null);
        else
            return result.success("수정 되었습니다.", accessGroup);

    }

    @DeleteMapping("/delete/{id}")
    public ApiResult delete(
            @PathVariable("id")String id
    ){
        ApiResult result = new ApiResult();

        if(accessGroupSv.delete(id))
            result.success("삭제되었습니다.");
        else
            result.fail("데이터를 확인해주세요.");

        return result;
    }
}
