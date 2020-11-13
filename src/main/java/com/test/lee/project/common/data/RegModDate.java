package com.test.lee.project.common.data;

import lombok.Data;

import java.util.Date;

@Data
public class RegModDate {
    Date regDate;

    Date modDate;

    public RegModDate() {
    }

    public RegModDate(Date regDate, Date modDate) {
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
