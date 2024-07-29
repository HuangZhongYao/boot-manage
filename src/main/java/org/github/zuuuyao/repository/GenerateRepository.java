package org.github.zuuuyao.repository;

import org.apache.ibatis.annotations.Param;
import org.github.zuuuyao.config.mybatis.extension.BaseMapperExtension;
import org.github.zuuuyao.entity.generate.ColumnEntity;
import org.github.zuuuyao.entity.generate.TableEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @Desc
 * @Time 2024-07-29 09:47
 * @Author HuangZhongYao
 */
@Repository
public interface GenerateRepository extends BaseMapperExtension<TableEntity> {

    /**
     * 获取数据库中的表信息
     * @return 表信息
     */
    List<TableEntity> getTables();

    /**
     * 获取表字段信息
     * @param tableName 表名
     * @return 字段信息
     */
    List<ColumnEntity> getTableColumns(@Param("tableName") String tableName);
}
