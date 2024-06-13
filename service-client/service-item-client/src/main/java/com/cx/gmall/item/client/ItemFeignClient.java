package com.cx.gmall.item.client;

import com.cx.gmall.common.result.Result;
import com.cx.gmall.item.client.impl.ItemDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xcx
 * @date 2024-06-11 15:01
 */
@FeignClient(value = "service-item",fallback = ItemDegradeFeignClient.class)
public interface ItemFeignClient {

    /**
     * 根据skuId获取sku详情
     * @param skuId
     * @return
     */
    @GetMapping("/api/item/{skuId}")
    public Result getSkuById(@PathVariable Long skuId);
}
