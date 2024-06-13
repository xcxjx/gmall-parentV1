package com.cx.gmall.product.api;

import com.cx.gmall.common.result.Result;
import com.cx.gmall.model.product.*;
import com.cx.gmall.product.service.ProductApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xcx
 * @date 2024-06-06 17:52
 */
@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    @Autowired
    private ProductApiService productApiService;

    /**
     * 根据skuId获取skuinfo信息
     * @param skuId
     * @return
     */
    @GetMapping("/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable Long skuId){
        SkuInfo skuInfo=productApiService.getSkuInfo(skuId);
        return skuInfo;
    }

    /**
     * 根据三级分类Id获取一级二级三级分类信息
     * @param category3Id
     * @return
     */
    @GetMapping("/inner/getCategoryView/{category3Id}")
    public BaseCategoryView getCategoryView(@PathVariable Long category3Id){
        BaseCategoryView baseCategoryView=productApiService.getCategoryView(category3Id);
        return baseCategoryView;
    }

    /**
     * 根据skuId获取商品价格
     * @param skuId
     * @return
     */
    @GetMapping("/inner/getSkuPrice/{skuId}")
    public BigDecimal getSkuPrice(@PathVariable Long skuId){
        return productApiService.getSkuPrice(skuId);
    }

    /**
     * 获取spu销售属性
     * @param skuId
     * @param spuId
     * @return
     */
    @GetMapping("/inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable Long skuId,
                                                          @PathVariable Long spuId){
        List<SpuSaleAttr> spuSaleAttrList=productApiService.getSpuSaleAttrListCheckBySku(skuId,spuId);
        return spuSaleAttrList;
    }

    /**
     * 根据spuid实现商品切换
     * @param spuId
     * @return
     */
    @GetMapping("/inner/getSkuValueIdsMap/{spuId}")
    public Map getSkuValueIdsMap(@PathVariable Long spuId){
        return productApiService.getSkuValueIdsMap(spuId);
    }
    /**
     * 根据spuId获取海报信息
     * @param spuId
     * @return
     */
    @GetMapping("/inner/findSpuPosterBySpuId/{spuId}")
    public List<SpuPoster> findSpuPosterBySpuId(@PathVariable Long spuId){

        return productApiService.findSpuPosterBySpuId(spuId);
    }

    /**
     * 根据skuid获取相关基本属性
     * @param skuId
     * @return
     */
    @GetMapping("/inner/getAttrList/{skuId}")
    public List<BaseAttrInfo> getAttrList(@PathVariable Long skuId){

        return productApiService.getAttrList(skuId);
    }
}
