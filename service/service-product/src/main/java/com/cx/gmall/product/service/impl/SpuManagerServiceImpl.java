package com.cx.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.model.product.*;
import com.cx.gmall.product.mapper.*;
import com.cx.gmall.product.service.SpuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-25 10:29
 */
@Service
public class SpuManagerServiceImpl implements SpuManagerService {

    @Autowired
    private SpuInfoMapper spuInfoMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuPosterMapper spuPosterMapper;

    /**
     * 根据三级分类Id获取spu列表
     * @param spuInfoPage
     * @param
     * @return
     */
    @Override
    public IPage<SpuInfo> getPageList(Page<SpuInfo> spuInfoPage, CategoryTrademarkVo categoryTrademarkVo) {
        QueryWrapper<SpuInfo> spuInfoQueryWrapper = new QueryWrapper<>();
        spuInfoQueryWrapper.eq("category3_id",categoryTrademarkVo.getCategory3Id());
        spuInfoQueryWrapper.orderByDesc("id");
        Page<SpuInfo> spuInfoPage1 = spuInfoMapper.selectPage(spuInfoPage, spuInfoQueryWrapper);
        return spuInfoPage1;
    }

    /**
     * 根据三级分类id获取spu列表
     * @param category3Id
     * @return
     */
    @Override
    public List<SpuInfo> getSpuList(Long category3Id) {
        QueryWrapper<SpuInfo> spuInfoQueryWrapper = new QueryWrapper<>();
        spuInfoQueryWrapper.eq("category3_id",category3Id);
        List<SpuInfo> spuInfoList = spuInfoMapper.selectList(spuInfoQueryWrapper);
        return spuInfoList;
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        spuInfoMapper.insert(spuInfo);

        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        /*
        * 销售属性和值新增
        * */
        if (!CollectionUtils.isEmpty(spuSaleAttrList)){
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insert(spuSaleAttr);
                if (spuSaleAttr!=null){
                    List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                    if (!CollectionUtils.isEmpty(spuSaleAttrValueList)){
                        for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                            spuSaleAttrValue.setSpuId(spuInfo.getId());
                            spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
                            spuSaleAttrValueMapper.insert(spuSaleAttrValue);
                        }
                    }
                }

            }
        }
        /*
        * 商品的图片新增
        * */
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (!CollectionUtils.isEmpty(spuImageList)){
            for (SpuImage spuImage : spuImageList) {
                spuImage.setSpuId(spuInfo.getId());
                spuImageMapper.insert(spuImage);
            }
        }
        /*
        * 商品的海报图片集合
        * */
        List<SpuPoster> spuPosterList = spuInfo.getSpuPosterList();
        if (!CollectionUtils.isEmpty(spuPosterList)){
            for (SpuPoster spuPoster : spuPosterList) {
                spuPoster.setSpuId(spuInfo.getId());
                spuPosterMapper.insert(spuPoster);
            }
        }
    }


}
