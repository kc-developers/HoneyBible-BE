package com.kwangchun.honeybible.dto;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class User {
    private final String member_num;
    private final String name;
    private final String ttolae;
    private final String birth_date;
    private final Date insert_dt;
    private final Date update_dt;
    private final String member_auth;
    private final String status;
}
