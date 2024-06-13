package com.cx.gmall.item.controller;

import com.cx.gmall.common.result.Result;
import com.cx.gmall.item.service.ItemApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xcx
 * @date 2024-06-06 17:44
 */
@RestController
@RequestMapping("/api/item")
public class ItemApiController {

    @Autowired
    private ItemApiService itemApiService;

    /**
     * 根据skuId获取sku详情
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}")
    public Result getSkuById(@PathVariable Long skuId){
        Map<String,Object> resultMap=itemApiService.getSkuById(skuId);
        return Result.ok(resultMap);
    }
}
