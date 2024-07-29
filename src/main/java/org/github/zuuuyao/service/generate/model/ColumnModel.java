package org.github.zuuuyao.service.generate.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;

import java.io.Serial;

/**
 * @Desc 代码生成器字段列实体
 * @Time 2024-07-26 14:36
 * @Author HuangZhongYao
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ColumnModel extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 3576845894314042340L;

    public ColumnModel() {
    }

    /**
     * 表名
     */
    @Schema(description = "表名")
    private String tableName;

    /**
     * 列名
     */
    @Schema(description = "列名")
    @ValidateNotNullAndEmpty(message = "不能为空")
    private String columnName;

    /**
     * 列数据类型
     */
    @Schema(description = "列数据类型")
    @ValidateNotNullAndEmpty(message = "不能为空")
    private String dataType;

    /**
     * 列备注
     */
    @Schema(description = "列备注")
    @ValidateNotNullAndEmpty(message = "不能为空")
    private String columnComment;

    /**
     * java属性名
     */
    private String attrName;

    /**
     * java属性类型
     */
    private String attrType;


}
