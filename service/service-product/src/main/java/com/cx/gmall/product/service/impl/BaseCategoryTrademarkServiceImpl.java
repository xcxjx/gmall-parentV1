package com.cx.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.gmall.model.product.BaseCategoryTrademark;
import com.cx.gmall.model.product.BaseTrademark;
import com.cx.gmall.model.product.CategoryTrademarkVo;
import com.cx.gmall.product.mapper.BaseCategoryTrademarkMapper;
import com.cx.gmall.product.mapper.BaseTrademarkMapper;
import com.cx.gmall.product.service.BaseCategoryTrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xcx
 * @date 2024-04-26 9:45
 */

@Service
public class BaseCategoryTrademarkServiceImpl extends ServiceImpl<BaseCategoryTrademarkMapper, BaseCategoryTrademark> implements BaseCategoryTrademarkService {

    @Autowired
    private BaseCategoryTrademarkMapper baseCategoryTrademarkMapper;
    @Autowired
    private BaseTrademarkMapper baseTrademarkMapper;

    /**
     * 根据三级分类id查询品牌列表
     *
     * @param category3Id
     * @return
     */
    @Override
    public List<BaseTrademark> findTrademarkList(Long category3Id) {
        QueryWrapper<BaseCategoryTrademark> baseCategoryTrademarkQueryWrapper = new QueryWrapper<>();
        baseCategoryTrademarkQueryWrapper.eq("category3_id", category3Id);
        List<BaseCategoryTrademark> baseCategoryTrademarkList = baseCategoryTrademarkMapper.selectList(baseCategoryTrademarkQueryWrapper);
        if (!CollectionUtils.isEmpty(baseCategoryTrademarkList)) {
            List<Long> trademarkIdList = baseCategoryTrademarkList.stream()
                    .map(BaseCategoryTrademark::getTrademarkId)
                    .collect(Collectors.toList());
            List<BaseTrademark> baseTrademarkList = baseTrademarkMapper.selectBatchIds(trademarkIdList);
            return baseTrademarkList;
        }
        return null;


    }

    /**
     * 新增品牌
     *
     * @param categoryTrademarkVo
     * @return
     */
    @Override
    public void save(CategoryTrademarkVo categoryTrademarkVo) {
        Long category3Id = categoryTrademarkVo.getCategory3Id();
        List<Long> trademarkIdList = categoryTrademarkVo.getTrademarkIdList();
        if (!CollectionUtils.isEmpty(trademarkIdList)) {
            List<BaseCategoryTrademark> baseCategoryTrademarkList = trademarkIdList.stream().map(trademarkId -> {
                BaseCategoryTrademark baseCategoryTrademark = new BaseCategoryTrademark();
                baseCategoryTrademark.setCategory3Id(category3Id);
                baseCategoryTrademark.setTrademarkId(trademarkId);
                return baseCategoryTrademark;
            }).collect(Collectors.toList());
            this.saveBatch(baseCategoryTrademarkList);
        }
    }

    /**
     * 新增品牌前查看哪些品牌可以新增
     *
     * @param category3Id
     * @return
     */
    @Override
    public List<BaseTrademark> findCurrentTrademarkList(Long category3Id) {

        //根据category3Id获取spu列表
        QueryWrapper<BaseCategoryTrademark> baseCategoryTrademarkQueryWrapper = new QueryWrapper<>();
        baseCategoryTrademarkQueryWrapper.eq("category3_id", category3Id);
        List<BaseCategoryTrademark> baseCategoryTrademarkList = baseCategoryTrademarkMapper.selectList(baseCategoryTrademarkQueryWrapper);
        //获取所有品牌
        List<BaseTrademark> baseTrademarkList = baseTrademarkMapper.selectList(null);
        //根据分类品牌中的品牌id获取品牌列表
        if (!CollectionUtils.isEmpty(baseCategoryTrademarkList)) {
            List<Long> baseTrademarkIdList = baseCategoryTrademarkList.stream().map(BaseCategoryTrademark::getTrademarkId).collect(Collectors.toList());
            return baseTrademarkList.stream().filter(baseTrademark -> !baseTrademarkIdList.contains(baseTrademark.getId())).collect(Collectors.toList());
        }
        return baseTrademarkList;
    }

    /**
     * 根据category3Id和trademarkId把品牌从分类品牌中删除
     * @param category3Id
     * @param trademarkId
     * @return
     */
    @Override
    public void delete(Long category3Id, Long trademarkId) {
        QueryWrapper<BaseCategoryTrademark> baseCategoryTrademarkQueryWrapper = new QueryWrapper<>();
        baseCategoryTrademarkQueryWrapper.eq("category3_id",category3Id);
        baseCategoryTrademarkQueryWrapper.eq("trademark_id",trademarkId);
        baseCategoryTrademarkMapper.delete(baseCategoryTrademarkQueryWrapper);
    }
}
