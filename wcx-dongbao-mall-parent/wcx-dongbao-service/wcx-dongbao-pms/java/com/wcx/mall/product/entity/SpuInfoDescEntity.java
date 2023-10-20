package com.wcx.mall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * spu信息介绍
 *
 * @author wcx
 * @email caixinwang@163.com
 * @date 2023-07-11 14:46:05
 */
@Data
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 * 如果这个主键不是自增的，那么我们就需要设置这个字段的类型为
	 * IdType.INPUT
	 */
	@TableId(type=IdType.INPUT)
	private Long spuId;
	/**
	 * 商品介绍
	 */
	private String decript;

}
