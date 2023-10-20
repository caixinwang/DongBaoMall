package com.wcx.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wcx.common.utils.PageUtils;
import com.wcx.mall.product.entity.CategoryEntity;
import com.wcx.mall.product.vo.Catalog2VO;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author wcx
 * @email caixinwang@163.com
 * @date 2023-07-11 14:46:05
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> queryPageWithTree(Map<String, Object> params);

    void removeCategoryByIds(List<Long> ids);

    Long[] findCatelogPath(Long catelogId);

    void updateDetail(CategoryEntity categoryEntity);

    List<CategoryEntity> getLeve1Category();

    Map<String, List<Catalog2VO>> getCatelog2JSON();
}

