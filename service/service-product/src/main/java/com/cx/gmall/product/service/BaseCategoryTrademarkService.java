package com.cx.gmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.gmall.model.product.BaseCategoryTrademark;
import com.cx.gmall.model.product.BaseTrademark;
import com.cx.gmall.model.product.CategoryTrademarkVo;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-26 9:45
 */
public interface BaseCategoryTrademarkService extends IService<BaseCategoryTrademark> {

    List<BaseTrademark> findTrademarkList(Long category3Id);

    void save(CategoryTrademarkVo categoryTrademarkVo);

    List<BaseTrademark> findCurrentTrademarkList(Long category3Id);

    void delete(Long category3Id, Long trademarkId);
}
