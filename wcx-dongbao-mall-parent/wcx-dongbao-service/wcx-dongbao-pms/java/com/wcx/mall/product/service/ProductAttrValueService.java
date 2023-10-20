package com.wcx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.common.utils.PageUtils;
import com.wcx.mall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author wcx
 * @email caixinwang@163.com
 * @date 2023-07-11 14:46:05
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据SPUID查询对应的规格参数
     * @param spuId
     * @return
     */
    List<ProductAttrValueEntity> baseAttrsForSpuId(Long spuId);
}

