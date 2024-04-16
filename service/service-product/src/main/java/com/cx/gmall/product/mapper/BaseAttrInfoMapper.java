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

    List<BaseAttrInfo> selectBaseAttrInfoList(@Param("category1Id") Long category1Id,
                                                @Param("category2Id") @PathVariable Long category2Id,
                                                @Param("category3Id") @PathVariable Long category3Id);
}
