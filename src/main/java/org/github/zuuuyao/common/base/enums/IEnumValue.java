package org.github.zuuuyao.common.base.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 枚举属性字段基类
 * @Desc 枚举基类
 * @Time 2024-07-11 14:58
 * @Author HuangZhongYao
 */
public interface IEnumValue extends IEnum<Integer> {

    /**
     * 获取枚举value,value对应数据库存储字段
     *
     * @return 枚举唯一value值
     */
    Integer getValue();
}
