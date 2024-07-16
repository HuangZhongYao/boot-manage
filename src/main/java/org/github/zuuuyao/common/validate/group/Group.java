package org.github.zuuuyao.common.validate.group;

/**
 * 分组校验
 *
 * @Desc
 * @Time 2024-07-16 14:09
 * @Author HuangZhongYao
 */
public interface Group {

    /**
     * 插入时
     */
    interface Insert {
    }

    /**
     * 更新时
     */
    interface Edit {
    }

    /**
     * 插入和更新时
     */
    interface InsertOrEdit extends Group {
    }
}
