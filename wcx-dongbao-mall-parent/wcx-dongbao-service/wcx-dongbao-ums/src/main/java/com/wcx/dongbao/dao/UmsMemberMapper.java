package com.wcx.dongbao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wcx.dongbao.ums.entity.UmsMember;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author caixinwang
 * @since 2023-05-26
 */
public interface UmsMemberMapper extends BaseMapper<UmsMember> {
    int countByUsername(UmsMember umsMember);

    UmsMember selectByUsername(String username);

}
