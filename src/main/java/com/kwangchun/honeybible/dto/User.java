package com.kwangchun.honeybible.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    @Hidden
    private String memberNum;
    @Schema(example = "홍길동")
    private final String name;
    @Schema(example = "50")
    private final String ttolae;
    @Schema(example = "19900101")
    private final String birthDate;
    @Schema(example = "N")
    private final String memberAuth;
    @Schema(example = "Y")
    private final String status;
    @Hidden
    private final Date insertDt;
    @Hidden
    private final Date updateDt;

    public User(String name, String ttolae, String birthDate, String memberAuth, String status) {
        this.memberNum = null;
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
