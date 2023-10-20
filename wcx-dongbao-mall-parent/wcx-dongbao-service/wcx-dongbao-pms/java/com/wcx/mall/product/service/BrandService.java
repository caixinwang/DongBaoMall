package com.wcx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.common.utils.PageUtils;
import com.wcx.mall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author wcx
 * @email caixinwang@163.com
 * @date 2023-07-11 14:46:05
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(BrandEntity brand);

}

