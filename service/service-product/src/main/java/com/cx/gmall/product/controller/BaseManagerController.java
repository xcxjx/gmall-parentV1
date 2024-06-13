package com.cx.gmall.product.controller;

import com.cx.gmall.common.result.Result;
import com.cx.gmall.model.product.*;
import com.cx.gmall.product.service.BaseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-14 22:03
 */
@RestController
@RequestMapping("/admin/product")
public class BaseManagerController {

    @Autowired
    private BaseManagerService baseManagerService;

    /**
     * 查询一级分类
     * @return
     */
    @GetMapping("/getCategory1")
    public Result getCategory1(){
        List<BaseCategory1> baseCategory1List =baseManagerService.getCategory1();
        return Result.ok(baseCategory1List);
    }

    /**
     *
     * 根据一级id查询二级分类
     * @param category1Id
     * @return
     */
    @GetMapping("/getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable Long category1Id){
        List<BaseCategory2> baseCategory2List=baseManagerService.getCategory2(category1Id);
        return Result.ok(baseCategory2List);
    }

    /**
     * 根据二级Id查询三级分类
     * @param category2Id
     * @return
     */
    @GetMapping("/getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable Long category2Id){
        List<BaseCategory3> baseCategory3List=baseManagerService.getCategory3(category2Id);
        return Result.ok(baseCategory3List);
    }

    /**
     * 根据三级分类id获取属性及属性值
     * @param category1Id 一级分类Id
     * @param category2Id 二级分类id
     * @param category3Id 三级分类id
     * @return
     */
    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable Long category1Id,
                               @PathVariable Long category2Id,
                               @PathVariable Long category3Id){

        List<BaseAttrInfo> baseAttrInfoList=baseManagerService.getAttrInfoList(category1Id,category2Id,category3Id);
        return Result.ok(baseAttrInfoList);
    }

    /**
     * 新增修改属性及属性值
     * @param baseAttrInfo
     * @return
     */
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        baseManagerService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }

    /**
     * 属性回显
     * @param attrId
     * @return
     */
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable Long attrId){
        List<BaseAttrValue> baseAttrValueList=baseManagerService.getAttrValueList(attrId);
        return Result.ok(baseAttrValueList);
    }
}
