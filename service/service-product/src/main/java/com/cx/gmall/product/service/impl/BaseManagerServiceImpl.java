package com.cx.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.gmall.model.product.*;
import com.cx.gmall.product.mapper.*;
import com.cx.gmall.product.service.BaseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xcx
 * @date 2024-04-15 15:07
 */
@Service
public class BaseManagerServiceImpl implements BaseManagerService {

    @Autowired
    private BaseCategory1Mapper baseCategory1Mapper;
    @Autowired
    private BaseCategory2Mapper baseCategory2Mapper;
    @Autowired
    private BaseCategory3Mapper baseCategory3Mapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;
    /**
     * 查询一级分类
     * @return
     */
    @Override
    public List<BaseCategory1> getCategory1() {
        return baseCategory1Mapper.selectList(null);
    }

    /**
     *
     * 根据一级id查询二级分类
     * @param category1Id
     * @return
     */
    @Override
    public List<BaseCategory2> getCategory2(Long category1Id) {
        QueryWrapper<BaseCategory2> baseCategory2QueryWrapper = new QueryWrapper<>();
        baseCategory2QueryWrapper.eq("category1_id",category1Id);
        return baseCategory2Mapper.selectList(baseCategory2QueryWrapper);
    }

    /**
     * 根据二级Id查询三分分类
     * @param category2Id
     * @return
     */
    @Override
    public List<BaseCategory3> getCategory3(Long category2Id) {
        QueryWrapper<BaseCategory3> baseCategory3QueryWrapper = new QueryWrapper<>();
        baseCategory3QueryWrapper.eq("category2_id",category2Id);
        return baseCategory3Mapper.selectList(baseCategory3QueryWrapper);
    }

    /**
     * 根据三级分类id获取属性及属性值
     * @param category1Id 一级分类Id
     * @param category2Id 二级分类id
     * @param category3Id 三级分类id
     * @return
     */
    @Override
    public List<BaseAttrInfo> getAttrInfoList(Long category1Id, Long category2Id, Long category3Id) {
        return baseAttrInfoMapper.selectBaseAttrInfoList(category1Id,category2Id,category3Id);
    }

    /**
     * 新增修改属性及属性值
     * @param baseAttrInfo
     * @return
     */
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {

        /*
         如果有id说明是修改，属性要修改，属性值要先删除在新增
         如果没有说明是新增，属性直接新增就好，属性值同修改一起新增
         */
        if (baseAttrInfo.getId()!=null){
            //修改属性
            baseAttrInfoMapper.updateById(baseAttrInfo);
            //删除属性值
            QueryWrapper<BaseAttrValue> baseAttrValueQueryWrapper = new QueryWrapper<>();
            baseAttrValueQueryWrapper.eq("attr_id",baseAttrInfo.getId());
            baseAttrValueMapper.delete(baseAttrValueQueryWrapper);
        }else {
            //新增属性
            baseAttrInfoMapper.insert(baseAttrInfo);
        }

        //新增属性值
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList!=null){
            for (BaseAttrValue baseAttrValue : attrValueList) {
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insert(baseAttrValue);
            }
        }

    }

    /**
     * 属性回显
     * @param attrId
     * @return
     */
    @Override
    public List<BaseAttrValue> getAttrValueList(Long attrId) {


        //根据属性id查找属性值
        QueryWrapper<BaseAttrValue> baseAttrValueQueryWrapper = new QueryWrapper<>();
        baseAttrValueQueryWrapper.eq("attr_id",attrId);
        List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.selectList(baseAttrValueQueryWrapper);


        return baseAttrValueList;
    }
}
