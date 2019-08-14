package com.opsigte.e.common.core.mapper;

import java.util.List;
import java.util.Map;


/**
 * <p> @ClassName: <i>BaseMapper<T></i></p>
 * <p> @Description: <i>基本的对数据库的crud操作以及分页方法的封装，所有的mapper都需要继承自此BaseMapper；使用时，按照方法名称在mapper.xml中写相应的sql</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/7/25 17:20</i></p>
 * <p> @Version: <i>V1.0</i> </p>
 */
public interface BaseMapper<T> {

    /**
     * 查询所有的数据
     * @return List
     */
    List<T> selectListAll();

    /**
     * 分页查询
     *
     * @param map 查询条件
     * @return List
     */
    List<T> selectListByPage(Map<String, Object> map);

    /**
     * 根据条件查询
     *
     * @param map 查询条件
     * @return List
     */
    List<T> selectListByMap(Map<String, Object> map);

    /**
     * 根据主键id查询
     *
     * @param id 主键id
     * @return T
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 修改表中所有的数据
     *
     * @param t 实体对象，字段为null也会修改
     * @return int 1-修改成功;0-修改失败
     */
    int updateByPrimaryKey(T t);

    /**
     * 根据条件修改表中数据
     * @param t 实体对象，字段为null不会修改
     * @return int 1-修改成功;0-修改失败
     */
    int updateByPrimaryKeySelective(T t);

    /**
     * 插入数据
     *
     * @param t 实体对象
     * @return int 1-插入成功;0-插入失败
     */
    Integer insert(T t);

    /**
     * 根据主键id删除数据
     * @param id 主键id
     * @return int 1-删除成功;0-删除失败
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据条件删除数据
     * @param map 删除条件
     * @return int 1-删除成功;0-删除失败
     */
    int deleteByMap(Map<String,Object> map);
}
