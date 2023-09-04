package com.wcx.dongbao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.dongbao.common.base.annotations.TokenCheck;
import com.wcx.dongbao.common.base.enums.StateCodeEnum;
import com.wcx.dongbao.common.base.result.DataObject;
import com.wcx.dongbao.common.base.result.ResultWrapper;
import com.wcx.dongbao.common.util.JwtUtil;
import com.wcx.dongbao.dao.UmsMemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcx.dongbao.ums.entity.UmsMember;
import com.wcx.dongbao.ums.entity.dto.UmsMemberEditParamDTO;
import com.wcx.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.wcx.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.wcx.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author caixinwang
 * @since 2023-05-26
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember>
        implements IService<UmsMember>, UmsMemberService {

    @Resource
    UmsMemberMapper umsMemberMapper;
    @Resource
    PasswordEncoder passwordEncoder;

    public ResultWrapper<UmsMember> register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterParamDTO, umsMember);
        int cnt = umsMemberMapper.countByUsername(umsMember);//检查是否重名
        if (cnt > 0) return ResultWrapper.getBuilder(StateCodeEnum.REGISTER_USERNAME_EXIST).build();
        umsMember.setPassword(passwordEncoder.encode(umsMember.getPassword()));//加密密码
        umsMemberMapper.insert(umsMember);
        return ResultWrapper.getBuilder(StateCodeEnum.REGISTER_SUCCESS).build();
    }

    public ResultWrapper login(UmsMemberLoginParamDTO dto) {
//        UmsMember umsMember = umsMemberMapper.selectByName(umsMemberRegisterParamDTO.getUsername());//手写mybatis
        UmsMember queryMember = new UmsMember();
        queryMember.setUsername(dto.getUsername());
        UmsMember umsMember = umsMemberMapper.selectOne(new QueryWrapper<UmsMember>().setEntity(queryMember));
        if (umsMember != null &&
                passwordEncoder.matches(dto.getPassword(), umsMember.getPassword())) {//登录成功
            String refreshToken= JwtUtil.createToken(dto.getUsername());//创建一个token
            String accessToken=JwtUtil.accessToken(refreshToken);
            return ResultWrapper.getBuilder(StateCodeEnum.LOGIN_SUCCESS)
                    .data(new DataObject()
                            .data("token", new DataObject()
                                    .data("refresh_token",refreshToken)
                                    .data("access_token",accessToken)
                                    .build())
                            .build())
                    .build();//后面说
        }
        return ResultWrapper.getBuilder(StateCodeEnum.LOGIN_FAIL).build();//登录失败
    }

    @Override
    public ResultWrapper edit(UmsMemberEditParamDTO dto,String token) {//不允许修改用户名,需要判断当前token有没有权限改
        String username=JwtUtil.parseToken(token);//只允许修改自己的信息
        UmsMember exist = umsMemberMapper.selectByUsername(username);//查出你要修改的用户名是否存在
        if (exist == null) return ResultWrapper.getBuilder(StateCodeEnum.EDIT_USER_NOT_EXIST)
                .build();//确定当前用户还存在
        UmsMember detail = new UmsMember();//仍然需要进行脱敏
        BeanUtils.copyProperties(dto, detail);
        detail.setPassword(passwordEncoder.encode(detail.getPassword()));
        int updates = umsMemberMapper.update(detail,
                new UpdateWrapper<UmsMember>().
                        setEntity(UmsMember.builder().
                                username(username).build()));
        return ResultWrapper.getBuilder(StateCodeEnum.EDIT_SUCCESS)
                .build();
    }

    @Override
    public ResultWrapper refreshToken(String token) {
        String refreshToken=JwtUtil.createToken(JwtUtil.parseToken(token));
        String accessToken=JwtUtil.accessToken(refreshToken);
        return ResultWrapper.getBuilder(StateCodeEnum.TOKEN_REFRESHED)
                .data(new DataObject()
                        .data("token", new DataObject()
                                .data("refresh_token",refreshToken)
                                .data("access_token",accessToken)
                                .build())
                        .build())
                .build();
    }


}
