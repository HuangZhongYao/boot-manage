package org.github.zuuuyao.common.base.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * 定义实体基础字段
 *
 * @Desc 实体基类
 * @Time 2024-07-11 14:56
 * @Author HuangZhongYao
 */
public class BaseEntity implements Serializable {

    /**
     * 序列化ID
     */
    @Serial
    private static final long serialVersionUID = -5915083783207513196L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
