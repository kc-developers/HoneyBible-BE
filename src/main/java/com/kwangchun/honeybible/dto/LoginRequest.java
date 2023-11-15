package com.kwangchun.honeybible.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginRequest {
    @Schema(example = "홍길동")
    private String username;
    @Schema(example = "2001")
    private String ttolae;
    @Schema(example = "0606")
    private String lastPhoneNum;
}
