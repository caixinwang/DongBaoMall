package com.wcx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.common.utils.PageUtils;
import com.wcx.mall.product.entity.AttrEntity;
import com.wcx.mall.product.vo.AttrGroupRelationVO;
import com.wcx.mall.product.vo.AttrResponseVo;
import com.wcx.mall.product.vo.AttrVO;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author wcx
 * @email caixinwang@163.com
 * @date 2023-07-11 14:46:05
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVO vo);

    PageUtils queryBasePage(Map<String, Object> params, Long catelogId, String attrType);

    AttrResponseVo getAttrInfo(Long attrId);

    void updateBaseAttr(AttrVO attr);

    void removeByIdsDetails(Long[] attrIds);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVO[] vos);

    PageUtils getNoAttrRelation(Map<String, Object> params, Long attrgroupId);


    List<Long> selectSearchAttrIds(List<Long> attrIds);
}

