package com.cx.gmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.gmall.model.product.BaseAttrInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-15 17:11
 */
@Mapper
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo> {

    /**
     * 根据一级，二级三级分类id查询基本属性信息
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    List<BaseAttrInfo> selectBaseAttrInfoList(@Param("category1Id") Long category1Id,
                                                @Param("category2Id") @PathVariable Long category2Id,
                                                @Param("category3Id") @PathVariable Long category3Id);

    /**
     * 根据skuId查询基本属性信息
     * @param skuId
     * @return
     */
    List<BaseAttrInfo> getAttrList(Long skuId);
}
