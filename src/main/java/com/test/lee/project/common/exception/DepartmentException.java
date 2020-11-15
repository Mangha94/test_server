package com.test.lee.project.common.exception;

import com.test.lee.project.common.type.DepartmentExceptionType;

public class DepartmentException extends Exception{
    private DepartmentExceptionType type;

    public DepartmentException(DepartmentExceptionType type){
        this.type = type;
    }

    @Override
    public String getMessage(){
        String msg = super.getMessage();

        if(type == DepartmentExceptionType.NOT_DATA){
            msg = "데이터가 존재하지 않습니다.";
        }

        if(type == DepartmentExceptionType.SAME_CODE){
            msg = "존재하는 코드입니다.";
        }

        return msg;
    }

    public DepartmentExceptionType getType(){
        return type;
    }

    public void setType(DepartmentExceptionType type){
        this.type = type;
    }
}
