package com.cx.gmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.model.product.SpuInfo;

/**
 * @author xcx
 * @date 2024-04-25 10:29
 */
public interface SpuManagerService {
    IPage<SpuInfo> getPageList(Page<SpuInfo> spuInfoPage,SpuInfo spuInfo);
}
