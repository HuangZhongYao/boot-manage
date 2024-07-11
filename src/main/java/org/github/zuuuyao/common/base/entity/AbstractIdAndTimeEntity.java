package org.github.zuuuyao.common.base.entity;

import java.io.Serial;
import java.util.Date;

/**
 * 抽象id和创建时间、更新时间实体类基类
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-11 22:51
 */
public abstract class AbstractIdAndTimeEntity  extends AbstractIdEntity{

    @Serial
    private static final long serialVersionUID = -2246057209586846556L;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
