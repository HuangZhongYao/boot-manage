package org.github.zuuuyao.common.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;

/**
 * 抽象id和创建时间、更新时间、创建人、更新人实体类基类
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-11 22:51
 */
public abstract class AbstractBaseEntity  extends AbstractIdAndTimeEntity{

    @Serial
    private static final long serialVersionUID = 7070481894407373661L;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private String updatedBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
