package org.github.zuuuyao.service.generate.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.github.zuuuyao.common.base.dto.BaseDTO;

import java.io.Serial;

/**
 * @Desc
 * @Time 2024-07-29 14:00
 * @Author HuangZhongYao
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnVO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 3640046138765040082L;

    /**
     * 字段所属表所在的数据库名称
     */
    @Schema(description = "字段所属表所在的数据库名称")
    private String tableSchema;

    /**
     * 表名
     */
    @Schema(description = "表名")
    private String tableName;

    /**
     * 列名
     */
    @Schema(description = "列名")
    private String columnName;

    /**
     * 字段类型
     */
    @Schema(description = "字段类型")
    private String dataType;

    /**
     * 字段注释
     */
    @Schema(description = "字段注释")
    private String columnComment;

    /**
     * 列主键 如：PRI
     */
    @Schema(description = "列主键 如：PRI")
    private String columnKey;

    /**
     * 扩展信息
     * 如果是自增主键值=auto_increment
     */
    @Schema(description = "扩展信息,如果是自增主键值=auto_increment")
    private String extra;
}
