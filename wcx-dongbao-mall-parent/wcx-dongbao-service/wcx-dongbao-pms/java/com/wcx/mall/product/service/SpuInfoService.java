package com.wcx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.common.utils.PageUtils;
import com.wcx.mall.product.entity.SpuInfoEntity;
import com.wcx.mall.product.vo.OrderItemSpuInfoVO;
import com.wcx.mall.product.vo.SpuInfoVO;

import java.util.List;
import java.util.Map;

/**
 * spu信息
 *
 * @author wcx
 * @email caixinwang@163.com
 * @date 2023-07-11 14:46:05
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuInfoVO spuInfoVo);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void up(Long spuId);

    List<OrderItemSpuInfoVO> getOrderItemSpuInfoBySpuId(Long[] spuIds);
}

