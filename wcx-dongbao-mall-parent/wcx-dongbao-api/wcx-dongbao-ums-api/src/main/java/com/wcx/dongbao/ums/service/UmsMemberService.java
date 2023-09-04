package com.wcx.dongbao.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.dongbao.common.base.result.ResultWrapper;
import com.wcx.dongbao.ums.entity.UmsMember;
import com.wcx.dongbao.ums.entity.dto.UmsMemberEditParamDTO;
import com.wcx.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.wcx.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author caixinwang
 * @since 2023-05-26
 */
public interface UmsMemberService extends IService<UmsMember> {
    ResultWrapper register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);
    ResultWrapper login(UmsMemberLoginParamDTO umsMemberLoginParamDTO);
    ResultWrapper edit(UmsMemberEditParamDTO umsMemberEditParamDTO,String token);

    ResultWrapper refreshToken(String token);
}
