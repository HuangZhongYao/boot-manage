package org.github.zuuuyao.common.util.tree;

import java.util.List;

/**
 * 通用树节点抽象接口,目的限制每个节点都有Id和ParentId和children属性
 * 要注意Id类型和ParentId类型是一致的,本来考虑兼容id和ParentId类型不一样的情况但是想想能这样写可以刀了你
 *
 * @param <Id> 节点Id类型
 * @Desc
 * @Time 2024-07-15 15:57
 * @Author HuangZhongYao
 */
public interface ITreeNode<Id> {

    /**
     * 获取节点Id
     *
     * @return 节点Id
     */
    Id getId();

    /**
     * 设置节点id
     *
     * @param id id
     */
    void setId(Id id);

    /**
     * 获取节点父Id
     *
     * @return 父节点Id
     */
    Id getParentId();

    /**
     * 设置父节点Id
     *
     * @param parentId 父节点Id
     */
    void setParentId(Id parentId);

    /**
     * 获取子节点
     *
     * @return 子节点集合
     */
    List<ITreeNode<Id>> getChildren();

    /**
     * 设置字节点
     *
     * @param children 子节点集合
     */
    void setChildren(List<ITreeNode<Id>> children);
}
