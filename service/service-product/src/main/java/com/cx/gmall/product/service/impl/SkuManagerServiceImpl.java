package com.cx.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.model.product.*;
import com.cx.gmall.product.mapper.*;
import com.cx.gmall.product.service.SkuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author xcx
 * @date 2024-05-31 11:33
 */
@Service
public class SkuManagerServiceImpl implements SkuManagerService {
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;
    /**
     * 根据spuId获取图片列表
     * @param spuId
     * @return
     */
    @Override
    public List<SpuImage> spuImageList(Long spuId) {
        QueryWrapper<SpuImage> spuImageQueryWrapper = new QueryWrapper<>();
        spuImageQueryWrapper.eq("spu_id",spuId);
        List<SpuImage> spuImageList = spuImageMapper.selectList(spuImageQueryWrapper);
        return spuImageList;
    }

    /**
     * 加载spu属性列表
     * @param spuId
     * @return
     */
    @Override
    public List<SpuSaleAttr> spuSaleAttrList(Long spuId) {
        List<SpuSaleAttr> spuSaleAttrList=spuSaleAttrMapper.spuSaleAttrList(spuId);
        return spuSaleAttrList;
    }

    /**
     * 新增sku详情
     * @param skuInfo
     * @return
     */
    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        skuInfoMapper.insert(skuInfo);
        if (skuInfo!=null){
            List<SkuImage> skuImageList = skuInfo.getSkuImageList();
            if (!CollectionUtils.isEmpty(skuImageList)){
                for (SkuImage skuImage : skuImageList) {
                    skuImage.setSkuId(skuInfo.getId());
                    skuImageMapper.insert(skuImage);
                }
            }
            List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
            if (!CollectionUtils.isEmpty(skuAttrValueList)){
                for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                    skuAttrValue.setSkuId(skuInfo.getId());
                    skuAttrValueMapper.insert(skuAttrValue);
                }
            }
            List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
            if (!CollectionUtils.isEmpty(skuSaleAttrValueList)){
                for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                    skuSaleAttrValue.setSkuId(skuInfo.getId());
                    skuSaleAttrValue.setSpuId(skuInfo.getSpuId());
                    skuSaleAttrValueMapper.insert(skuSaleAttrValue);
                }
            }
        }
    }

    /**
     * 获取skuinfo信息
     * @param
     * @param
     * @return
     */
    @Override
    public IPage<SkuInfo> list(Page<SkuInfo> skuInfoPage) {
        return skuInfoMapper.selectPage(skuInfoPage, null);
    }

    /**
     * 上架
     * @param skuId
     * @return
     */
    @Override
    public void onSale(Long skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setIsSale(1);
        skuInfoMapper.updateById(skuInfo);
    }

    /**
     * 下架
     * @param skuId
     * @return
     */
    @Override
    public void cancelSale(Long skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setIsSale(0);
        skuInfoMapper.updateById(skuInfo);
    }
}
