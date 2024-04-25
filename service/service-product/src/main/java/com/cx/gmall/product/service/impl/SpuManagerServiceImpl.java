package com.cx.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.model.product.SpuInfo;
import com.cx.gmall.product.mapper.SpuInfoMapper;
import com.cx.gmall.product.service.SpuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xcx
 * @date 2024-04-25 10:29
 */
@Service
public class SpuManagerServiceImpl implements SpuManagerService {

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    /**
     * 根据三级分类Id获取spu列表
     * @param spuInfoPage
     * @param spuInfo
     * @return
     */
    @Override
    public IPage<SpuInfo> getPageList(Page<SpuInfo> spuInfoPage,SpuInfo spuInfo) {
        QueryWrapper<SpuInfo> spuInfoQueryWrapper = new QueryWrapper<>();
        spuInfoQueryWrapper.eq("category3_id",spuInfo.getCategory3Id());
        Page<SpuInfo> spuInfoPage1 = spuInfoMapper.selectPage(spuInfoPage, spuInfoQueryWrapper);
        return spuInfoPage1;
    }
}
