package com.hao.time.service;

import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface BaseService<T> {

	/**
     * 查询所有
     */
    public List<T> selectAll();

    /**
     * 根据主键查询
     */
    public T selectByPrimaryKey(T key);

    /**
     * 根据条件实例查询
     */
    public List<T> selectByExample(Example example);

    /**
     * 根据条件实例查询一个对象
     */
    public T selectOne(T record);

    /**
     * 新增
     */
    public int insert(T record);

    /**
     * 选择新增
     */
    public int insertSelective(T record);

    /**
     * 根据主键新增
     */
    public int updateByPrimaryKey(T record);

    /**
     * 根据主键选择新增
     */
    public int updateByPrimaryKeySelective(T record);

    /**
     * 根据属性更新
     */
    public int updateByExample(T record, Example example);

    /**
     * 根据属性选择更新
     */
    public int updateByExampleSelective(T record, Example example);

    /**
     * 删除
     */
    public int delete(T record);


    /**
     * 根据主键删除
     */
    public int deleteByPrimaryKey(Object key);

    /**
     * 批量删除
     */
    @Transactional
    public void deleteBatchByPrimaryKey(List<T> record) throws RuntimeException;

    /**
     * 根据属性删除
     */
    public int deleteByExample(Example example);

    /**
     * 分页查询所有
     */
    public PageInfo<T> selectAllByList(Integer pageNum, Integer pageSize);

    /**
     * 根据属性分页查询
     */
    public PageInfo<T> selectByList(T record, Integer pageNum, Integer pageSize);

    /**
     * 根据example分页查询
     */
    public PageInfo<T>  selectByExampleList(Example example, Integer pageNum, Integer pageSize) ;

    /**
     * 分页查询所有
     */
    public PageInfo<T>  selectAllByOffSetList(Integer offset, Integer limit);

    /**
     * 根据属性分页查询
     */
    public PageInfo<T> selectByOffSetList(T record, Integer offset, Integer limit);

    /**
     * 根据example分页查询
     */
    public PageInfo<T> selectByExampleOffSetList(Example example, Integer offset, Integer limit);
	
}
