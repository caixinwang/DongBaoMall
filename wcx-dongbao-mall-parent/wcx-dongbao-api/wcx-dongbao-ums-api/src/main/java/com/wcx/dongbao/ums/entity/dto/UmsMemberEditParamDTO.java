package com.wcx.dongbao.ums.entity.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UmsMemberEditParamDTO {
    @Size(min = 6,max=20,message = "密码长度在6~20之间")
    private String password;
    private String icon;//头像
    @Email
    private String email;
    private String nickName;
}
