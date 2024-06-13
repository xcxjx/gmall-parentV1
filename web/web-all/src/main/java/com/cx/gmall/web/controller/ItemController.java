package com.cx.gmall.web.controller;

import com.cx.gmall.common.result.Result;
import com.cx.gmall.item.client.ItemFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author xcx
 * @date 2024-06-11 15:45
 */
@RequestMapping
@Controller
@SuppressWarnings("all")
public class ItemController {

    @Autowired
    private ItemFeignClient itemFeignClient;

    @RequestMapping("{skuId}.html")
    public String getItem(@PathVariable Long skuId, Model model){
        Result<Map> skuById = itemFeignClient.getSkuById(skuId);
        model.addAllAttributes(skuById.getData());
        return "item/item";
    }
}
