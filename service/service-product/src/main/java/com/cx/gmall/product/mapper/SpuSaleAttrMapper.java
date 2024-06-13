package com.cx.gmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.gmall.model.product.SpuSaleAttr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xcx
 * @date 2024-05-20 11:16
 */
@Mapper
public interface SpuSaleAttrMapper extends BaseMapper<SpuSaleAttr> {

     List<SpuSaleAttr> spuSaleAttrList(Long spuId);

     List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId);

}
