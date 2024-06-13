package com.cx.gmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.model.product.SkuInfo;
import com.cx.gmall.model.product.SpuImage;
import com.cx.gmall.model.product.SpuSaleAttr;

import java.util.List;

/**
 * @author xcx
 * @date 2024-05-31 11:33
 */
public interface SkuManagerService {
    /**
     * 根据spuId获取图片列表
     * @param spuId
     * @return
     */
    List<SpuImage> spuImageList(Long spuId);

    /**
     * 加载spu属性列表
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> spuSaleAttrList(Long spuId);

    /**
     * 新增sku详情
     * @param skuInfo
     * @return
     */
    void saveSkuInfo(SkuInfo skuInfo);

    /**
     * 获取skuinfo信息
     * @param
     * @return
     */
    IPage<SkuInfo> list(Page<SkuInfo> skuInfoPage);

    /**
     * 上架
     * @param skuId
     * @return
     */
    void onSale(Long skuId);

    /**
     * 下架
     * @param skuId
     * @return
     */
    void cancelSale(Long skuId);

}
