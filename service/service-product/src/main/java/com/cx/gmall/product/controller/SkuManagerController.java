package com.cx.gmall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.common.result.Result;
import com.cx.gmall.model.product.SkuInfo;
import com.cx.gmall.model.product.SpuImage;
import com.cx.gmall.model.product.SpuSaleAttr;
import com.cx.gmall.product.service.SkuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xcx
 * @date 2024-05-31 11:12
 */
@RequestMapping("/admin/product")
@RestController
public class SkuManagerController {

    @Autowired
    private SkuManagerService skuManagerService;

    /**
     * 根据spuId获取图片列表
     * @param spuId
     * @return
     */
    @GetMapping("/spuImageList/{spuId}")
    public Result spuImageList(@PathVariable Long spuId){
        List<SpuImage> spuImageList =skuManagerService.spuImageList(spuId);
        return Result.ok(spuImageList);
    }

    /**
     * 加载spu属性列表
     * @param spuId
     * @return
     */
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable Long spuId){
        List<SpuSaleAttr> spuSaleAttrList=skuManagerService.spuSaleAttrList(spuId);
        return Result.ok(spuSaleAttrList);
    }

    /**
     * 新增sku详情
     * @param skuInfo
     * @return
     */
    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){
        skuManagerService.saveSkuInfo(skuInfo);
        return Result.ok();
    }

    /**
     * 获取skuinfo信息
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list/{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit){
        Page<SkuInfo> skuInfoPage = new Page<>(page, limit);
        IPage<SkuInfo> skuInfoIPage=skuManagerService.list(skuInfoPage);
        return Result.ok(skuInfoIPage);
    }

    /**
     * 上架
     * @param skuId
     * @return
     */
    @GetMapping("/onSale/{skuId}")
    public Result onSale(@PathVariable Long skuId){
        skuManagerService.onSale(skuId);
        return Result.ok();
    }

    /**
     * 下架
     * @param skuId
     * @return
     */
    @GetMapping("/cancelSale/{skuId}")
    public Result cancelSale(@PathVariable Long skuId){
        skuManagerService.cancelSale(skuId);
        return Result.ok();
    }
}
