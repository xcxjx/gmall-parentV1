package com.cx.gmall.item.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cx.gmall.item.service.ItemApiService;
import com.cx.gmall.model.product.*;
import com.cx.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xcx
 * @date 2024-06-06 17:46
 */
@Service
@SuppressWarnings("all")
public class ItemApiServiceImpl implements ItemApiService {

    @Autowired
    private ProductFeignClient productFeignClient;
    @Override
    public Map<String, Object> getSkuById(Long skuId) {
        Map<String,Object> resultMap=new HashMap<>();

        //获取skuinfo信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);

        if (skuInfo!=null){
            resultMap.put("skuInfo",skuInfo);
            //获取分类信息
            BaseCategoryView categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
            resultMap.put("categoryView",categoryView);
            //获取销售属性及值信息
            List<SpuSaleAttr> spuSaleAttrListCheckBySku = productFeignClient.getSpuSaleAttrListCheckBySku(skuId, skuInfo.getSpuId());
            resultMap.put("spuSaleAttrList",spuSaleAttrListCheckBySku);

            //获取sku下的销售属性值id组合
            Map skuValueIdsMap = productFeignClient.getSkuValueIdsMap(skuInfo.getSpuId());
            //将Map转换成页面需要的json对象
            String jsonString = JSON.toJSONString(skuValueIdsMap);
            resultMap.put("valuesSkuJson",jsonString);
            //获取价格信息
            BigDecimal skuPrice = productFeignClient.getSkuPrice(skuId);
            resultMap.put("price",skuPrice);
            //获取海报信息
            List<SpuPoster> spuPosterBySpuId = productFeignClient.findSpuPosterBySpuId(skuInfo.getSpuId());
            resultMap.put("spuPosterList",spuPosterBySpuId);
            //获取基本属性信息
            List<BaseAttrInfo> attrList = productFeignClient.getAttrList(skuId);
            if (attrList!=null){
                List<Map<String, Object>> skuAttrList = attrList.stream().map(baseAttrInfo -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("attrName", baseAttrInfo.getAttrName());
                    map.put("attrValue", baseAttrInfo.getAttrValueList().get(0).getValueName());
                    return map;
                }).collect(Collectors.toList());
                resultMap.put("skuAttrList",skuAttrList);
            }
        }
        return resultMap;
    }
}
