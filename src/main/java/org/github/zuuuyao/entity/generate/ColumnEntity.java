package org.github.zuuuyao.entity.generate;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * information_schema.COLUMNS 表字段元数据信息
 *
 * @Desc mysql表字段元数据信息
 * @Time 2024-07-29 12:37
 * @Author HuangZhongYao
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColumnEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2747780647812877476L;

    /**
     * 字段所属表所在的数据库名称
     */
    private String tableSchema;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 字段注释
     */
    private String columnComment;

    /**
     * 列主键 如：PRI
     */
    private String columnKey;

    /**
     * 扩展信息
     * 如果是自增主键值=auto_increment
     */
    private String extra;
}
