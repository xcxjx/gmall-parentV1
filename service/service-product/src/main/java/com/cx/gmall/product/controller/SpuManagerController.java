package com.cx.gmall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.common.result.Result;
import com.cx.gmall.model.product.SpuInfo;
import com.cx.gmall.product.service.SpuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xcx
 * @date 2024-04-25 10:19
 */
@RestController
@RequestMapping("/admin/product")
public class SpuManagerController {

    @Autowired
    private SpuManagerService spuManagerService;


    /**
     * 根据三级分类Id获取spu列表
     * @param page
     * @param limit
     * @param spuInfo
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public Result getPageList(@PathVariable Long page,
                              @PathVariable Long limit,
                              @RequestBody SpuInfo spuInfo){
        Page<SpuInfo> spuInfoPage = new Page<>(page, limit);
        IPage<SpuInfo> spuInfoIPage =spuManagerService.getPageList(spuInfoPage,spuInfo);
        return Result.ok(spuInfoIPage);
    }
}
