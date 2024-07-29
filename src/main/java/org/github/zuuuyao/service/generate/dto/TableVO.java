package org.github.zuuuyao.service.generate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.BaseDTO;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Desc
 * @Time 2024-07-29 09:56
 * @Author HuangZhongYao
 */
@Getter
@Setter
@ToString
@Schema(description = "表信息VO")
public class TableVO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = -1554440026971856364L;

    /**
     * 表所属的数据库名称
     */
    @Schema(description = "表所属的数据库名称")
    private String tableSchema;

    /**
     * 表名
     */
    @Schema(description = "表名")
    private String tableName;

    /**
     * 表注释
     */
    @Schema(description = "表注释")
    private String tableComment;

    /**
     * 存储引擎
     */
    @Schema(description = "存储引擎")
    private String engine;

    /**
     * 表的类型
     * 列如：是BASE TABLE（基本表）或 VIEW（视图）
     */
    @Schema(description = "表的类型: BASE TABLE（基本表）或 VIEW（视图）")
    private String tableType;

    /**
     * 表排序规则
     */
    @Schema(description = "表排序规则")
    private String tableCollation;

    /**
     * 表最后更新时间
     */
    @Schema(description = "表最后更新时间")
    private LocalDateTime updateTime;

}
