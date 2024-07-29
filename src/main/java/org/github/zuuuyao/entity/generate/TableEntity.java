package org.github.zuuuyao.entity.generate;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * information_schema.TABLES mysql表元数据信息
 * @Desc mysql表元数据信息
 * @Time 2024-07-29 09:38
 * @Author HuangZhongYao
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("information_schema.tables")
public class TableEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -3340552722434732276L;

    /**
     * 表所属的数据库名称
     */
    private String tableSchema;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 存储引擎
     */
    private String engine;

    /**
     * 表的类型
     * 列如：是BASE TABLE（基本表）或 VIEW（视图）
     */
    private String tableType;

    /**
     * 表排序规则
     */
    private String tableCollation;

    /**
     * 表创建时间
     */
    private LocalDateTime createTime;

    /**
     * 表最后更新时间
     */
    private LocalDateTime updateTime;
}
