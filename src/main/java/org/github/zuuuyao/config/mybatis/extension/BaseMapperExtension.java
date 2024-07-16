package org.github.zuuuyao.config.mybatis.extension;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.SqlSession;
import org.github.zuuuyao.common.util.ModelMapperUtil;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * BaseMapper扩展
 *
 * @Desc BaseMapper扩展
 * @Time 2024-07-12 09:25
 * @Author HuangZhongYao
 */
public interface BaseMapperExtension<TEntity> extends BaseMapper<TEntity> {

    default <U> U selectOne(Wrapper<TEntity> queryWrapper, Class<U> uClass) {
        TEntity entity = this.selectOne(queryWrapper);
        return ModelMapperUtil.map(entity, uClass);
    }

    default <U> U selectOne(Wrapper<TEntity> queryWrapper, Class<U> uClass, Consumer<U> consumer) {
        TEntity entity = this.selectOne(queryWrapper);
        return ModelMapperUtil.map(entity, uClass, consumer);
    }

    default <U> U selectOne(Wrapper<TEntity> queryWrapper, Class<U> uClass,
                            BiConsumer<TEntity, U> biConsumer) {
        TEntity entity = this.selectOne(queryWrapper);
        return ModelMapperUtil.map(entity, uClass, biConsumer);
    }

    /**
     * 查询并转换类型
     *
     * @param queryWrapper 查询条件
     * @param uClass       转换目标类型
     * @param <U>          目标类型
     * @return 转换后类型结果
     */
    default <U> List<U> selectList(Wrapper<TEntity> queryWrapper, Class<U> uClass) {
        List<TEntity> records = this.selectList(queryWrapper);
        return ModelMapperUtil.mapList(records, uClass);
    }

    /**
     * 查询并转换类型并且对转换结果处理
     *
     * @param queryWrapper 查询条件
     * @param uClass       转换目标类型
     * @param consumer     对转换结果处理函数
     * @param <U>          目标类型
     * @return 转换后类型结果
     */
    default <U> List<U> selectList(Wrapper<TEntity> queryWrapper, Class<U> uClass,
                                   Consumer<U> consumer) {
        List<TEntity> records = this.selectList(queryWrapper);
        return ModelMapperUtil.mapList(records, uClass, consumer);
    }

    /**
     * 查询并转换类型并且对转换结果处理
     *
     * @param queryWrapper 查询条件
     * @param uClass       转换目标类型
     * @param biConsumer   对转换结果处理函数
     * @param <U>          目标类型
     * @return 转换后类型结果
     */
    default <U> List<U> selectList(Wrapper<TEntity> queryWrapper, Class<U> uClass,
                                   BiConsumer<TEntity, U> biConsumer) {
        List<TEntity> records = this.selectList(queryWrapper);
        return ModelMapperUtil.mapList(records, uClass, biConsumer);
    }


    /**
     * 分页查询并转换查询结果
     *
     * @param pageIndex    页码
     * @param pageSize     页大小
     * @param queryWrapper 查询条件
     * @param uClass       将查询结果转换为指定DTO类型
     * @param <U>          指定的DTO类型
     * @return Page
     */
    default <U> Page<U> selectPage(long pageIndex, long pageSize, Wrapper<TEntity> queryWrapper,
                                    Class<U> uClass) {
        // 分页条件
        Page page = new Page(pageIndex, pageSize);
        // 执行分页查询
        Page<U> pageResult = this.selectPage(page, queryWrapper);
        // 设置数据
        pageResult.setRecords(ModelMapperUtil.mapList(pageResult.getRecords(), uClass));
        return pageResult;
    }

    /**
     * 分页查询并转换查询结果
     *
     * @param page         分页条件
     * @param queryWrapper 查询条件
     * @param uClass       将查询结果转换为指定DTO类型
     * @param <U>          指定的DTO类型
     * @return Page
     */
    default <U> Page<U> selectPage(Page page, Wrapper<TEntity> queryWrapper,
                                    Class<U> uClass) {
        // 执行分页查询
        Page<U> pageResult = this.selectPage(page, queryWrapper);
        // 设置数据
        pageResult.setRecords(ModelMapperUtil.mapList(pageResult.getRecords(), uClass));
        return pageResult;
    }

    /**
     * 分页查询并转换查询结果，并对转换后数据处理
     *
     * @param pageIndex    页码
     * @param pageSize     页大小
     * @param queryWrapper 查询条件
     * @param uClass       将查询结果转换为指定DTO类型
     * @param consumer     对转换后的数据处理逻辑
     * @param <U>          指定的DTO类型
     * @return Page
     */
    default <U> Page<U> selectPage(long pageIndex, long pageSize, Wrapper<TEntity> queryWrapper,
                                    Class<U> uClass, Consumer<U> consumer) {

        // 分页条件
        Page page = new Page(pageIndex, pageSize);
        // 执行分页查询
        Page<U> pageResult = this.selectPage(page, queryWrapper);
        // 转换类型并处理
        List<U> uResult = ModelMapperUtil.mapList(pageResult.getRecords(), uClass, consumer);
        // 设置数据
        pageResult.setRecords(uResult);
        return pageResult;
    }

    /**
     * 分页查询并转换查询结果，并对转换后数据处理
     *
     * @param page         分页条件
     * @param queryWrapper 查询条件
     * @param uClass       将查询结果转换为指定DTO类型
     * @param consumer     对转换后的数据处理逻辑
     * @param <U>          指定的DTO类型
     * @return Page
     */
    default <U> Page<U> selectPage(Page page, Wrapper<TEntity> queryWrapper,
                                    Class<U> uClass, Consumer<U> consumer) {

        // 执行分页查询
        Page<U> pageResult = this.selectPage(page, queryWrapper);
        // 转换类型并处理
        List<U> uResult = ModelMapperUtil.mapList(pageResult.getRecords(), uClass, consumer);
        // 设置数据
        pageResult.setRecords(uResult);
        return pageResult;
    }


    /**
     * 分页查询并转换查询结果，并对元数据和转换后数据处理
     *
     * @param pageIndex    页码
     * @param pageSize     页大小
     * @param queryWrapper 查询条件
     * @param uClass       将查询结果转换为指定DTO类型
     * @param biconsumer   对原数据和转换后的数据处理逻辑
     * @param <U>          指定的DTO类型
     * @return Page
     */
    default <U> Page<U> selectPage(long pageIndex, long pageSize, Wrapper<TEntity> queryWrapper,
                                    Class<U> uClass, BiConsumer<TEntity, U> biconsumer) {

        // 分页条件
        Page page = new Page(pageIndex, pageSize);
        // 执行分页查询
        Page pageResult = this.selectPage(page, queryWrapper);
        // 转换类型并处理
        List<U> uResult = ModelMapperUtil.mapList(pageResult.getRecords(), uClass, biconsumer);
        // 设置数据
        pageResult.setRecords(uResult);
        return pageResult;
    }

    /**
     * 分页查询并转换查询结果，并对元数据和转换后数据处理
     *
     * @param page         分页条件
     * @param queryWrapper 查询条件
     * @param uClass       将查询结果转换为指定DTO类型
     * @param biconsumer   对原数据和转换后的数据处理逻辑
     * @param <U>          指定的DTO类型
     * @return Page
     */
    default <U> Page<U> selectPage(Page page, Wrapper<TEntity> queryWrapper,
                                    Class<U> uClass, BiConsumer<TEntity, U> biconsumer) {

        // 执行分页查询
        Page pageResult = this.selectPage(page, queryWrapper);
        // 转换类型并处理
        List<U> uResult = ModelMapperUtil.mapList(pageResult.getRecords(), uClass, biconsumer);
        // 设置数据
        pageResult.setRecords(uResult);
        return pageResult;
    }


    /**
     * 获取批量操作 SqlSession
     *
     * @param zClass 实体类型
     * @return SqlSession
     */
    default SqlSession sqlSessionBatch(Class<TEntity> zClass) {
        return null;
    }

}
