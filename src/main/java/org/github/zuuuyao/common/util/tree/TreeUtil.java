package org.github.zuuuyao.common.util.tree;

import java.util.List;

/**
 * 通用树工具类
 *
 * @Desc
 * @Time 2024-07-15 15:55
 * @Author HuangZhongYao
 */
public final class TreeUtil {


    public static <Id> List<ITreeNode<Id>> listToTree(List<ITreeNode<Id>> nodeList) {

        // 根节点集合
        List<ITreeNode<Id>> topNodelist = nodeList
            .stream()
            .filter(nodeListItem -> null == nodeListItem.getParentId())
            .toList();

        // 树集合
        topNodelist.forEach(topNode -> getChildNodes(topNode, nodeList));

        return topNodelist;
    }


    /**
     * 递归获取节点的子节点
     *
     * @param node     当前节点
     * @param nodeList 节点集合
     * @param <Id>     节点Id类型也是ParentId类型
     */
    private static <Id> void getChildNodes(ITreeNode<Id> node,
                                           List<ITreeNode<Id>> nodeList) {

        // 判断当前节点为空或者节点集合为空则返回一个空集合
        if (node == null || nodeList == null || nodeList.isEmpty()) {
            return;
        }

        // 查找出下级,把下级放入一个集合中。然后把集合作为方法返回值返回
        List<ITreeNode<Id>> children = nodeList
            .stream()
            .filter(nodeListItem -> node.getId().equals(nodeListItem.getParentId()))
            .toList();
        // 设置子级
        node.setChildren(children);

        // 递归查找子级的子级
        children.forEach(childrenNode -> getChildNodes(childrenNode, nodeList));

    }
}
