package com.cx.gmall.item.client.impl;

import com.cx.gmall.common.result.Result;
import com.cx.gmall.item.client.ItemFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author xcx
 * @date 2024-06-11 15:02
 */
@Component
public class ItemDegradeFeignClient implements ItemFeignClient {
    @Override
    public Result getSkuById(Long skuId) {
        return Result.fail();
    }
}
