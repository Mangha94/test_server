package com.test.lee.project.controller;

import com.test.lee.project.common.data.ApiResult;
import com.test.lee.project.model.member.MemberSv;
import com.test.lee.project.model.member.data.Member;
import com.test.lee.project.model.member.searchData.MemberSearchData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class memberCt {

    final private MemberSv memberSv;

    @PostMapping("/list")
    public ApiResult get(
            @RequestBody MemberSearchData searchData
    ){
        ApiResult result = new ApiResult();
        List<Member> list = memberSv.gets(searchData);

        return result.success("success", list);
    }

    @GetMapping("/detail/{memberID}")
    public ApiResult detail(
            @PathVariable("memberID") String memberID
    ){
        ApiResult result = new ApiResult();
        Member member = memberSv.get(memberID);
        if(member == null)
            return result.fail("데이터가 존재하지 않습니다.", null);
        else
            return result.success("success", member);
    }
}
