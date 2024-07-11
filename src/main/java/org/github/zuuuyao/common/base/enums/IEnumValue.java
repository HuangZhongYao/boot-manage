package org.github.zuuuyao.common.base.enums;

/**
 * 为了让所有枚举都有value属性
 * @Desc 枚举基类
 * @Time 2024-07-11 14:58
 * @Author HuangZhongYao
 */
public interface IEnumValue {

    /**
     * 获取枚举value
     * @return 枚举唯一value值
     */
    int getValue();
}
