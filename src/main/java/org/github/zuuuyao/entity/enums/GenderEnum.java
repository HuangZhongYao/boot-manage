package org.github.zuuuyao.entity.enums;

import lombok.Getter;
import org.github.zuuuyao.common.base.enums.IEnumsValue;

/**
 * @Desc 性别枚举
 * @Time 2024-07-11 16:30
 * @Author HuangZhongYao
 */
@Getter
public enum GenderEnum implements IEnumsValue {
    /**
     * 未知
     */
    UNKNOWN(0,"保密"),

    /**
     * 男
     */
    MALE(1,"男"),

    /**
     * 女
     */
    FEMALE(2,"女"),
    ;

    /**
     * 枚举对应数据库存储值
     */
    private final Integer value;

    /**
     * 描述
     */
    private final String desc;

    GenderEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
