package org.github.zuuuyao.entity.enums;

import lombok.Getter;
import org.github.zuuuyao.common.base.enums.IEnumsValue;

/**
 * 资源类型枚举
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-14 2:18
 */
@Getter
public enum ResourcesTypeEnum implements IEnumsValue {
    MENU(1, "菜单"),
    DIRECTORY(2, "目录"),
    BUTTON(3, "按钮"),
    ;

    /**
     * 枚举对应数据库存储值
     */
    private final Integer value;

    /**
     * 描述
     */
    private final String desc;

    ResourcesTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
