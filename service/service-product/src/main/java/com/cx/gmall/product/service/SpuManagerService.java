package com.cx.gmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.model.product.CategoryTrademarkVo;
import com.cx.gmall.model.product.SpuInfo;

import java.util.List;

/**
 * @author xcx
 * @date 2024-05-31 11:30
 */
public interface SpuManagerService {

    IPage<SpuInfo> getPageList(Page<SpuInfo> spuInfoPage, CategoryTrademarkVo categoryTrademarkVo);

    void saveSpuInfo(SpuInfo spuInfo);

    List<SpuInfo> getSpuList(Long category3Id);

}
