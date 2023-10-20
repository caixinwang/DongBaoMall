package com.wcx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.common.utils.PageUtils;
import com.wcx.mall.product.entity.SkuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * sku图片
 *
 * @author wcx
 * @email caixinwang@163.com
 * @date 2023-07-11 14:46:05
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuImagesEntity> getImagesBySkuId(Long skuId);
}

