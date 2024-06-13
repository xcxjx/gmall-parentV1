package com.cx.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.gmall.model.product.*;
import com.cx.gmall.product.mapper.*;
import com.cx.gmall.product.service.ProductApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xcx
 * @date 2024-06-06 20:05
 */
@Service
public class ProductApiServiceImpl implements ProductApiService {
    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private BaseCategoryViewMapper baseCategoryViewMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuPosterMapper spuPosterMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    /**
     * 根据skuId获取skuinfo信息
     * @param skuId
     * @return
     */
    @Override
    public SkuInfo getSkuInfo(Long skuId) {

        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        QueryWrapper<SkuImage> skuImageQueryWrapper = new QueryWrapper<>();
        skuImageQueryWrapper.eq("sku_id",skuId);
        List<SkuImage> skuImageList = skuImageMapper.selectList(skuImageQueryWrapper);
        if (skuInfo!=null){
            skuInfo.setSkuImageList(skuImageList);
        }
        return skuInfo;
    }

    /**
     * 根据skuId获取商品价格
     * @param skuId
     * @return
     */
    @Override
    public BigDecimal getSkuPrice(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        if (skuInfo!=null){
            return skuInfo.getPrice();
        }
        return new BigDecimal("0");
    }

    /**
     * 获取spu销售属性
     * @param skuId
     * @param spuId
     * @return
     */
    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {
        return spuSaleAttrMapper.getSpuSaleAttrListCheckBySku(skuId, spuId);
    }

    /**
     * 根据spuId获取海报信息
     * @param spuId
     * @return
     */
    @Override
    public List<SpuPoster> findSpuPosterBySpuId(Long spuId) {
        QueryWrapper<SpuPoster> spuPosterQueryWrapper = new QueryWrapper<>();
        spuPosterQueryWrapper.eq("spu_id",spuId);
        return spuPosterMapper.selectList(spuPosterQueryWrapper);
    }

    /**
     * 根据spuid实现商品切换
     * @param spuId
     * @return
     */
    @Override
    public Map getSkuValueIdsMap(Long spuId) {
        Map<Object,Object> resultMap=new HashMap<>();
        List<Map> mapList=skuSaleAttrValueMapper.getSkuValueIdsMap(spuId);
        if (mapList!=null && mapList.size()>0){
            for (Map map : mapList) {
                resultMap.put(map.get("value_ids"),map.get("sku_id"));
            }

        }
        return resultMap;
    }

    /**
     * 根据三级分类Id获取一级二级三级分类信息
     * @param category3Id
     * @return
     */
    @Override
    public BaseCategoryView getCategoryView(Long category3Id) {
        return baseCategoryViewMapper.selectById(category3Id);
    }

    /**
     * 根据skuid获取相关基本属性
     * @param skuId
     * @return
     */
    @Override
    public List<BaseAttrInfo> getAttrList(Long skuId) {
        return baseAttrInfoMapper.getAttrList(skuId);
    }
}
