package com.wcx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.common.utils.PageUtils;
import com.wcx.mall.product.entity.SkuInfoEntity;
import com.wcx.mall.product.vo.SpuItemVO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * sku信息
 *
 * @author wcx
 * @email caixinwang@163.com
 * @date 2023-07-11 14:46:05
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageByCondition(Map<String, Object> params);

    List<SkuInfoEntity> getSkusBySpuId(Long spuId);

    SpuItemVO item(Long skuId) throws ExecutionException, InterruptedException;

    List<String> getSkuSaleAttrs(Long skuId);
}

