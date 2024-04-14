package com.cx.gmall.product.controller;

import com.cx.gmall.common.result.Result;
import com.cx.gmall.model.product.BaseCategory1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-14 22:03
 */
@RestController
@RequestMapping("/admin/product")
public class BaseManagerController {

    @GetMapping("/getCategory1")
    public Result getCategory1(){
        List<BaseCategory1> baseCategory1List;
        return Result.ok();
    }
}
