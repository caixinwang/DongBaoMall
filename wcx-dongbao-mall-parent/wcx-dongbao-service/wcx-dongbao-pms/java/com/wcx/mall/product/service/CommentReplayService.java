package com.wcx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.common.utils.PageUtils;
import com.wcx.mall.product.entity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author wcx
 * @email caixinwang@163.com
 * @date 2023-07-11 14:46:05
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

