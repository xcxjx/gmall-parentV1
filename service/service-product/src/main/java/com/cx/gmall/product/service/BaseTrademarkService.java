package com.cx.gmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.model.product.BaseTrademark;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-25 20:27
 */
public interface BaseTrademarkService {

    IPage<BaseTrademark> getPageList(Page<BaseTrademark> baseTrademarkPage);

    BaseTrademark getById(Long id);

    void save(BaseTrademark baseTrademark);

    void removeById(Long id);

    void updateById(BaseTrademark baseTrademark);

    List<BaseTrademark> getTrademarkList();

}
