package org.github.zuuuyao.entity.dict;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.entity.AbstractBaseEntity;

/**
 * 字典类型实体
 *
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
@TableName("sys_dict_type")
public class DictTypeEntity extends AbstractBaseEntity {

    @Serial
    private static final long serialVersionUID = -4909560354235113011L;

    /**
     * 上级
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

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
