package com.kwangchun.honeybible.dto;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
    private final String memberNum;
    private final String name;
    private final String ttolae;
    private final String birthDate;
    private final String memberAuth;
    private final String status;
    private final Date insertDt;
    private final Date updateDt;

    public User(String memberNum, String name, String ttolae, String birthDate, String memberAuth, String status) {
        this.memberNum = memberNum;
        this.name = name;
        this.ttolae = ttolae;
        this.birthDate = birthDate;
        this.memberAuth = memberAuth;
        this.status = status;

        Date currentDate = new Date();
        insertDt = currentDate;
        updateDt = currentDate;
    }
}
