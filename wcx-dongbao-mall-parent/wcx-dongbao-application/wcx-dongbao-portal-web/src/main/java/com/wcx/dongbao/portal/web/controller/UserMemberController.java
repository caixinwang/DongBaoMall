package com.wcx.dongbao.portal.web.controller;

import com.wcx.dongbao.common.base.annotations.TokenCheck;
import com.wcx.dongbao.common.base.result.ResultWrapper;
import com.wcx.dongbao.ums.entity.dto.UmsMemberEditParamDTO;
import com.wcx.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.wcx.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.wcx.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-member")
public class UserMemberController {

    @Autowired
    UmsMemberService umsMemberService;

    @PostMapping("/register")
    public ResultWrapper register(@RequestBody @Valid UmsMemberRegisterParamDTO umsMemberRegisterParamDTO){
        return umsMemberService.register(umsMemberRegisterParamDTO);
    }

    @PostMapping("/login")
    public ResultWrapper login(@RequestBody @Valid UmsMemberLoginParamDTO umsMemberLoginParamDTO){
        return umsMemberService.login(umsMemberLoginParamDTO);
    }

    @PostMapping("/edit")
    @TokenCheck
    public ResultWrapper edit(@RequestBody @Valid UmsMemberEditParamDTO umsMemberEditParamDTO,@RequestHeader String token){
        return umsMemberService.edit(umsMemberEditParamDTO,token);
    }

    @PostMapping("/refresh-token")
    @TokenCheck
    public ResultWrapper refreshToken(@RequestHeader String token){
        return umsMemberService.refreshToken(token);
    }



}
