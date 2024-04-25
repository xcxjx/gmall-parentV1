package com.cx.gmall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.common.result.Result;
import com.cx.gmall.model.product.BaseTrademark;
import com.cx.gmall.product.service.BaseTrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-25 20:24
 */
@RestController
@RequestMapping("/admin/product/baseTrademark")
public class BaseTrademarkController {

    @Autowired
    private BaseTrademarkService baseTrademarkService;

    /**
     * 获取品牌列表
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("{page}/{limit}")
    public Result getPageList(@PathVariable Long page,
                              @PathVariable Long limit){
        Page<BaseTrademark> baseTrademarkPage = new Page<>(page,limit);
        IPage<BaseTrademark> baseTrademarkIPage=baseTrademarkService.getPageList(baseTrademarkPage);
        return Result.ok(baseTrademarkIPage);
    }

    /**
     * 根据id获取品牌，进行数据回显
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Long id){
        BaseTrademark baseTrademark=baseTrademarkService.getById(id);
        return Result.ok(baseTrademark);
    }

    /**
     * 新增品牌
     * @param baseTrademark
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }

    /**
     * 根据id删除品牌
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    public Result removeById(@PathVariable Long id){
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

    /**
     * 根据id修改品牌
     * @param baseTrademark
     * @return
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }

    /**
     * 查询品牌列表
     * @return
     */
    @GetMapping("/getTrademarkList")
    public Result getTrademarkList(){
        List<BaseTrademark> baseTrademarkList=baseTrademarkService.getTrademarkList();
        return Result.ok(baseTrademarkList);
    }
}
