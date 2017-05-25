package com.ynnjii.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ynnjii.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/5/25
 * description：
 */
public abstract class BaseServiceImp <T,M extends BaseMapper<T>> implements BaseService<T> {


    @Autowired
    private M mapper;

    public M getMapper() {
        return mapper;
    }

    /**
     * 批量选择
     * @param bundle
     * @return
     */
    public List<T> select(T bundle) {
        return mapper.select(bundle);
    }

    /**
     * 单个查询
     * @param bundle
     * @return
     */
    public T selectOne(T bundle){
        List<T> list = mapper.select(bundle);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 批量插入
     * @param bundle
     * @return
     */
    @Transactional
    @Override
    public int insert(List<T> bundle) {
        return mapper.insert(bundle);
    }

    /**
     * 单个添加
     * @param bundle
     * @return
     */
    @Transactional
    @Override
    public int insert(T bundle) {
        List<T> list = new ArrayList<T>();
        list.add(bundle);
        return mapper.insert(list);
    }

    /**
     * 批量升级
     * @param bundle
     * @return
     */
    @Transactional
    @Override
    public int update(List<T> bundle) {
        return mapper.update(bundle);
    }

    /**
     * 单个更新
     * @param bundle
     * @return
     */
    @Transactional
    @Override
    public int update(T bundle) {
        List<T> list = new ArrayList<T>();
        list.add(bundle);
        return mapper.update(list);
    }

    /**
     * 批量删除
     * @param bundle
     * @return
     */
    @Transactional
    @Override
    public int delete(T bundle) {
        return mapper.delete(bundle);
    }

    @Transactional
    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public T selectById(Integer id) {
        return mapper.selectById(id);
    }

    /**
     * 通过分页批量查询
     * @param bundle
     * @param page
     * @return
     */
    @Override
    public Page<T> selectByPage(T bundle, Page page) {
        Page<T> p= PageHelper.startPage(page.getPageNum(),page.getPageSize());
        if (!StringUtils.isNullOrEmpty(page.getOrderBy())) {
            p.setOrderBy(page.getOrderBy());
        }
        select(bundle);
        return p;
    }


    /**
     * 得到用户名
     * @return
     */
    protected String getUserName(){
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

}
