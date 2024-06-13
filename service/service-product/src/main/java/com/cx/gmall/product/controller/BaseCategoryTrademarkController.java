package com.cx.gmall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.gmall.common.result.Result;
import com.cx.gmall.model.product.BaseCategoryTrademark;
import com.cx.gmall.model.product.BaseTrademark;
import com.cx.gmall.model.product.CategoryTrademarkVo;
import com.cx.gmall.product.service.BaseCategoryTrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-26 9:33
 */
@RestController
@RequestMapping("/admin/product/baseCategoryTrademark")
public class BaseCategoryTrademarkController {

    @Autowired
    private BaseCategoryTrademarkService baseCategoryTrademarkService;

    /**
     * 根据三级分类id查询品牌列表
     * @param category3Id
     * @return
     */
    @GetMapping("/findTrademarkList/{category3Id}")
    public Result findTrademarkList(@PathVariable Long category3Id){
        List<BaseTrademark> baseTrademarkList=baseCategoryTrademarkService.findTrademarkList(category3Id);
        return Result.ok(baseTrademarkList);
    }

    /**
     * 新增品牌前查看哪些品牌可以新增
     * @param category3Id
     * @return
     */
    @GetMapping("/findCurrentTrademarkList/{category3Id}")
    public Result findCurrentTrademarkList(@PathVariable Long category3Id){
        List<BaseTrademark> baseTrademarkList=baseCategoryTrademarkService.findCurrentTrademarkList(category3Id);
        return Result.ok(baseTrademarkList);
    }

    /**
     * 新增品牌
     * @param categoryTrademarkVo
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody CategoryTrademarkVo categoryTrademarkVo){
        baseCategoryTrademarkService.save(categoryTrademarkVo);
        return Result.ok();
    }

    /**
     * 根据category3Id和trademarkId把品牌从分类品牌中删除
     * @param category3Id
     * @param trademarkId
     * @return
     */
    @DeleteMapping("/remove/{category3Id}/{trademarkId}")
    public Result remove(@PathVariable Long category3Id,
                         @PathVariable Long trademarkId){

        baseCategoryTrademarkService.delete(category3Id,trademarkId);
        return Result.ok();
    }
}
