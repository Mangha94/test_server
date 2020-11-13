package com.test.lee.project.controller;

import com.test.lee.project.common.data.ApiResult;
import com.test.lee.project.model.member.MemberSv;
import com.test.lee.project.model.member.dto.LoginData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginCt {

    final private MemberSv memberSv;

    @PostMapping("/loginAct")
    public ApiResult doLogin(
            @Valid @RequestBody LoginData loginData
    ){
        ApiResult apiResult = new ApiResult();

        if(memberSv.login(loginData))
            apiResult.success("success");
        else
            apiResult.fail("로그인에 실패했습니다.");

        return apiResult;
    }
}
