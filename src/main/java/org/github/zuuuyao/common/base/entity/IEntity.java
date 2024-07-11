package org.github.zuuuyao.common.base.entity;

import java.io.Serializable;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-11 22:58
 */
public interface IEntity extends Serializable {

    /**
     * 获取主键id
     * @return 主键
     */
    Long getId();
}
