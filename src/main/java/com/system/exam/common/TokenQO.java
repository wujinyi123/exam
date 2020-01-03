package com.system.exam.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenQO {
    @NotBlank(message = "token不能为空")
    private String token;
}
