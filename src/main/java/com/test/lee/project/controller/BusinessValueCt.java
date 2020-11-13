package com.test.lee.project.controller;

import com.test.lee.project.common.data.ApiResult;
import com.test.lee.project.model.businessValue.BusinessValueSv;
import com.test.lee.project.model.businessValue.data.BusinessValue;
import com.test.lee.project.model.businessValue.dto.RegistBusinessValue;
import com.test.lee.project.model.businessValue.searchData.ValueSearchData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/businessValue")
public class BusinessValueCt {

    final private BusinessValueSv businessValueSv;

    @PostMapping("/list")
    public ApiResult get(
            @RequestBody ValueSearchData searchData
    ){
        ApiResult result = new ApiResult();
        List<BusinessValue> list = businessValueSv.gets(searchData);

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

        BusinessValue businessValue = businessValueSv.get(id);

        if(businessValue == null)
            return result.fail("등록된 정보가 없습니다.", null);
        else
            return result.success("success", businessValue);
    }

    @PostMapping("/insert")
    public ApiResult insert(
            @RequestBody RegistBusinessValue registBusinessValue
    ){
        ApiResult result = new ApiResult();

        BusinessValue businessValue = businessValueSv.insert(registBusinessValue);

        if(businessValue == null)
            return result.fail("등록에 실패하였습니다.", null);
        else
            return result.success("등록 되었습니다.", businessValue);
    }

    @PostMapping("/update/{id}")
    public ApiResult update(
            @PathVariable("id")String id,
            @RequestBody RegistBusinessValue registBusinessValue
    ){
        ApiResult result = new ApiResult();

        BusinessValue businessValue = businessValueSv.update(id, registBusinessValue);

        if(businessValue == null)
            return result.fail("수정에 실패하였습니다.", null);
        else
            return result.success("수정 되었습니다.", businessValue);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult delete(
            @PathVariable("id")String id
    ){
        ApiResult result = new ApiResult();

        if(businessValueSv.delete(id))
            result.success("삭제 되었습니다.");
        else
            result.fail("존재하지 않는 데이터입니다.");

        return result;
    }
}
