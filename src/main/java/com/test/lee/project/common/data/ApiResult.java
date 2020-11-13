package com.test.lee.project.common.data;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 기본 리턴 값
 * Created by PPARK_SE on 2018-08-31.
 */
@Data
public class ApiResult {

    /**
     * 성공 여부
     */
    private boolean success;

    /**
     * 코드값
     */
    private int code;

    /**
     * 로그인 으로 던지는 경우
     */
    private boolean loginBool = false;

    /**
     * 메시지
     */
    private String message;

    /**
     * 데이터
     */
    private Object data;

    /**
     * 맵데이터
     */
    private Map<String, Object> mapData;

    public ApiResult(){
        success = true;
        message = "";
    }

    public ApiResult(boolean success, String errMessage) {
        this.success = success;
        this.message = errMessage;
    }

    public void success(String message){
        success = true;
        this.message = message;
    }

    public ApiResult success (String message, Object data)
    {
        success = true;
        this.message = message;
        this.data = data;

        return this;
    }

    public void fail(String message){
        success = false;
        this.message = message;
    }

    public ApiResult fail (String message, Object data)
    {
        success = false;
        this.message = message;
        this.data = data;

        return this;
    }

    public ApiResult failCode (String message, int code)
    {
        success = false;
        this.message = message;
        this.code = code;

        return this;
    }

    public void failCatch(String message){
        success = false;
        loginBool = true;
        this.message = message;
    }

    public void addObject (String key, Object data)
    {
        if (mapData == null)
            mapData = new HashMap<>();

        mapData.put (key, data);
    }

}
