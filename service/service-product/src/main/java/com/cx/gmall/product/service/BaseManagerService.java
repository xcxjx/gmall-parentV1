package com.cx.gmall.product.service;

import com.cx.gmall.model.product.*;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-15 15:07
 */
public interface BaseManagerService {
    List<BaseCategory1> getCategory1();

    List<BaseCategory2> getCategory2(Long category1Id);

    List<BaseCategory3> getCategory3(Long category2Id);

    List<BaseAttrInfo> getAttrInfoList(Long category1Id, Long category2Id, Long category3Id);

    void saveAttrInfo(BaseAttrInfo baseAttrInfo);


    List<BaseAttrValue> getAttrValueList(Long attrId);
}
