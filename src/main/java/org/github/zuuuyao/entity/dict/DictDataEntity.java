package org.github.zuuuyao.entity.dict;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.entity.AbstractBaseEntity;

/**
 * 字典数据实体
 * @Desc Created by IntelliJ IDEA.
 * @Author ZhongYao.Huang (https://github.com/HuangZhongYao)
 * @Copyright ZuuuuYao By Github
 * @Time 2024-08-18 3:42
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict_data")
public class DictDataEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = -4909560354235113011L;

    /**
     * 字典数据类型id
     */
    private Long dictTypeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 启用状态
     */
    private Boolean enable;

    /**
     * 备注
     */
    private String remark;
}
