package org.github.zuuuyao.service.generate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Desc 代码生成器字段列实体
 * @Time 2024-07-26 14:36
 * @Author HuangZhongYao
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ColumnModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 3576845894314042340L;

    /**
     * 表名
     */
    private String tableName;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列名类型
     */
    private String dataType;
    /**
     * 列名备注
     */
    private String columnComment;

    /**
     * java属性名
     */
    private String attrName;

    /**
     * java属性类型
     */
    private String attrType;

    public ColumnModel() {
    }

}
