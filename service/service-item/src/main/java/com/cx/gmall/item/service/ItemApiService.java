package com.cx.gmall.item.service;

import java.util.Map;

/**
 * @author xcx
 * @date 2024-06-06 17:45
 */
public interface ItemApiService {
    Map<String, Object> getSkuById(Long skuId);
}
