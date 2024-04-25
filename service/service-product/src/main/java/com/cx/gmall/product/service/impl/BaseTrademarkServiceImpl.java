package com.cx.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.gmall.model.product.BaseTrademark;
import com.cx.gmall.product.mapper.BaseTrademarkMapper;
import com.cx.gmall.product.service.BaseTrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-25 20:27
 */
@Service
public class BaseTrademarkServiceImpl implements BaseTrademarkService {
    @Autowired
    private BaseTrademarkMapper baseTrademarkMapper;

    /**
     * 获取品牌列表
     * @param baseTrademarkPage
     * @return
     */
    @Override
    public IPage<BaseTrademark> getPageList(Page<BaseTrademark> baseTrademarkPage) {
        return baseTrademarkMapper.selectPage(baseTrademarkPage,null);
    }

    /**
     * 根据id获取品牌，进行数据回显
     * @param id
     * @return
     */
    @Override
    public BaseTrademark getById(Long id) {
        return baseTrademarkMapper.selectById(id);
    }

    /**
     * 新增品牌
     * @param baseTrademark
     * @return
     */
    @Override
    public void save(BaseTrademark baseTrademark) {
        baseTrademarkMapper.insert(baseTrademark);
    }

    /**
     * 根据id删除品牌
     * @param id
     * @return
     */
    @Override
    public void removeById(Long id) {
        baseTrademarkMapper.deleteById(id);
    }

    /**
     * 根据id修改品牌
     * @param baseTrademark
     * @return
     */
    @Override
    public void updateById(BaseTrademark baseTrademark) {
        baseTrademarkMapper.updateById(baseTrademark);
    }

    /**
     * 查询品牌列表
     * @return
     */
    @Override
    public List<BaseTrademark> getTrademarkList() {
        return baseTrademarkMapper.selectList(null);
    }
}
