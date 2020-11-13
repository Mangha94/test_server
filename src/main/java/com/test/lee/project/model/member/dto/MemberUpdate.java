package com.test.lee.project.model.member.dto;

import lombok.Data;

@Data
public class MemberUpdate {
    private String memberID;

    private String name;

    private String phone;

    private String spot;

    private String position;
}
