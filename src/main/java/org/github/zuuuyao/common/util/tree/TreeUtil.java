package org.github.zuuuyao.common.util.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用树工具类
 *
 * @Desc
 * @Time 2024-07-15 15:55
 * @Author HuangZhongYao
 */
public final class TreeUtil {


    public static <Id> List<ITreeNode<Id>> listToTree() {
        return null;
    }


    /**
     * 获取节点的子节点
     *
     * @param node     当前节点
     * @param nodeList 节点集合
     * @param <Id>     节点Id类型也是ParentId类型
     * @return 子节点集合
     */
    private static <Id> List<ITreeNode<Id>> getChildNodes(ITreeNode<Id> node, List<ITreeNode<Id>> nodeList) {

        // 判断当前节点为空或者节点集合为空则返回一个空集合
        if (node == null || nodeList == null || nodeList.isEmpty()) {
            return new ArrayList<ITreeNode<Id>>();
        }

        // 查找出下级,把下级放入一个集合中。然后把集合作为方法返回值返回
        return nodeList
                .stream()
                .filter(nodeListItem -> node.getId().equals(nodeListItem.getParentId()))
                .toList();

    }

    /**
     * 获取节点的子节点
     *
     * @param nodeId   当前节点Id
     * @param nodeList 节点集合
     * @param <Id>     节点Id类型也是ParentId类型
     * @return 子节点集合
     */
    private static <Id> List<ITreeNode<Id>> getChildNodes(Id nodeId, List<ITreeNode<Id>> nodeList) {

        // 判断当前节点为空或者节点集合为空则返回一个空集合
        if (nodeId == null || nodeList == null || nodeList.isEmpty()) {
            return new ArrayList<ITreeNode<Id>>();
        }

        // 查找出下级,把下级放入一个集合中。然后把集合作为方法返回值返回
        return nodeList
                .stream()
                .filter(nodeListItem -> nodeId.equals(nodeListItem.getParentId()))
                .toList();

    }
}
