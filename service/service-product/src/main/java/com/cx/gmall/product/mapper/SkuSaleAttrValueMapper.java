package com.cx.gmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.gmall.model.product.SkuSaleAttrValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author xcx
 * @date 2024-06-04 17:05
 */
@Mapper
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {
    List<Map> getSkuValueIdsMap(Long spuId);
}
