package com.cx.gmall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.common.result.Result;
import com.cx.gmall.model.product.BaseSaleAttr;
import com.cx.gmall.model.product.CategoryTrademarkVo;
import com.cx.gmall.model.product.SpuInfo;
import com.cx.gmall.product.service.BaseManagerService;
import com.cx.gmall.product.service.SpuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-25 10:19
 */
@RestController
@RequestMapping("/admin/product")
public class SpuManagerController {

    @Autowired
    private SpuManagerService spuManagerService;
    @Autowired
    private BaseManagerService baseManagerService;


    /**
     * 根据三级分类Id获取spu列表
     * @param page
     * @param limit
     * @param
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public Result getPageList(@PathVariable Long page,
                              @PathVariable Long limit,
                              CategoryTrademarkVo categoryTrademarkVo){
        Page<SpuInfo> spuInfoPage = new Page<>(page, limit);
        IPage<SpuInfo> spuInfoIPage =spuManagerService.getPageList(spuInfoPage,categoryTrademarkVo);
        return Result.ok(spuInfoIPage);
    }

    /**
     * 获取销售属性
     * @return
     */
    @GetMapping("/baseSaleAttrList")
    public Result baseSaleAttrList(){
        List<BaseSaleAttr> baseSaleAttrList=baseManagerService.baseSaleAttrList();
        return Result.ok(baseSaleAttrList);
    }

    /**
     * 新增spu相关纤细
     * @param spuInfo
     * @return
     */
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        spuManagerService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

    /**
     * 根据三级分类id获取spu列表
     * @param category3Id
     * @return
     */
    @GetMapping("/spuList/{category3Id}")
    public Result getSpuList(@PathVariable Long category3Id){
        List<SpuInfo> spuInfoList=spuManagerService.getSpuList(category3Id);
        return Result.ok(spuInfoList);
    }

}
