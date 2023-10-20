package com.wcx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.common.utils.PageUtils;
import com.wcx.mall.product.entity.AttrGroupEntity;
import com.wcx.mall.product.vo.AttrGroupWithAttrsVo;
import com.wcx.mall.product.vo.SpuItemGroupAttrVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author wcx
 * @email caixinwang@163.com
 * @date 2023-07-11 14:46:05
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);


    List<AttrGroupWithAttrsVo> getAttrgroupWithAttrsByCatelogId(Long catelogId);

    List<SpuItemGroupAttrVo> getAttrgroupWithSpuId(Long spuId
            , Long catalogId);
}

