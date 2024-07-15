package org.github.zuuuyao.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import org.github.zuuuyao.common.util.tree.ITreeNode;
import org.github.zuuuyao.config.mybatis.extension.BaseMapperExtension;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.entity.system.RoleEntity;
import org.springframework.stereotype.Repository;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-13 18:39
 */
@Repository
public interface ResourcesRepository extends BaseMapperExtension<ResourcesEntity> {
    List<ITreeNode<Long>> selectList(QueryWrapper<Object> objectQueryWrapper, Class<ITreeNode> iTreeNodeClass);
}
