package org.github.zuuuyao.service.generate.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.github.zuuuyao.common.base.dto.BaseDTO;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;
import org.github.zuuuyao.common.validate.ValidateWord;
import org.github.zuuuyao.service.generate.model.ColumnModel;

import java.io.Serial;
import java.util.List;

/**
 * @Desc
 * @Time 2024-07-29 13:43
 * @Author HuangZhongYao
 */
@Schema(description = "生成代码入参DTO")
@Getter
@Setter
public class CodeGenerateInputDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = -6020011231247015617L;

    @Schema(description = "表名", requiredMode = Schema.RequiredMode.REQUIRED, example = "sys_user")
    @ValidateNotNullAndEmpty(message = "表名不能为空")
    private String tableName;

    @Schema(description = "表注释", requiredMode = Schema.RequiredMode.REQUIRED, example = "用户表")
    @ValidateNotNullAndEmpty(message = "表注释不能为空")
    private String tableComment;

    @Schema(description = "模块名", requiredMode = Schema.RequiredMode.REQUIRED, example = "user")
    @ValidateNotNullAndEmpty(message = "模块名不能为空")
    @ValidateWord(message = "模块名只允许为小写字母")
    private String moduleName;

    @Schema(description = "源码包名", requiredMode = Schema.RequiredMode.REQUIRED, example = "org.github.zuuuyao")
    @ValidateNotNullAndEmpty(message = "模块名不能为空")
    private String packageName;

    @Schema(description = "所属菜单Id,如果为 null 则是一级菜单", example = "1")
    private Long parentMenuId;

    @Schema(description = "是否添加到资源管理,自动添加到资源管理", example = "true")
    @ValidateNotNullAndEmpty(message = "是否添加到资源管理不能为空")
    private Boolean addResources;

    @ValidateNotNullAndEmpty(message = "字段信息不能为空")
    @Schema(description = "字段信息集合", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ColumnModel> columns;
}
