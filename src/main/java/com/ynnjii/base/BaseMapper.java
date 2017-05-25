package com.ynnjii.base;

import java.util.List;

/**
 * project：ynnjii-web
 * class：mapper base
 * author：yzh
 * date：2017/5/25
 * description：
 */
public interface BaseMapper<T> {

    /**
     * 批量查询
     * @param bundle
     * @return
     */
    List<T> select(T bundle);

    /**
     * 批量添加
     * @param bundle
     * @return
     */
    int insert(List<T> bundle);

    /**
     * 批量更新
     * @param bundle
     * @return
     */
    int update(List<T> bundle);

    /**
     * 删除
     * @param bundle
     * @return
     */
    int delete(T bundle);

    /**
     * 根据id删除
     */
    int deleteById(Integer id);

    /**
     * 根据id查询
     */
    T selectById(Integer id);
}
