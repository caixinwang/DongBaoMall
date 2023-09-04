package com.wcx.dongbao.ums.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UmsMemberLoginParamDTO {
    @NotEmpty(message = "用户名不为空")
    private String username;
    @NotEmpty(message = "密码不为空")
    private String password;
}
