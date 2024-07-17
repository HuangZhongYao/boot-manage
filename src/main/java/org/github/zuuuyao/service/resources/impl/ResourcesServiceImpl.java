package org.github.zuuuyao.service.resources.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.common.util.tree.ITreeNode;
import org.github.zuuuyao.common.util.tree.TreeUtil;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.entity.system.RoleResourcesEntity;
import org.github.zuuuyao.repository.ResourcesRepository;
import org.github.zuuuyao.repository.RoleResourcesRepository;
import org.github.zuuuyao.service.resources.IResourcesService;
import org.github.zuuuyao.service.resources.dto.input.AddResourcesInputDTO;
import org.github.zuuuyao.service.resources.model.ResourcesTreeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-14 2:33
 */
@AllArgsConstructor
@Service
public class ResourcesServiceImpl implements IResourcesService {

    ResourcesRepository resourcesRepository;
    RoleResourcesRepository roleResourcesRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delResources(BaseManyLongIdInputDTO inputDTO) {

        // 删除资源关联角色中间表数据
        roleResourcesRepository.delete(Wrappers.<RoleResourcesEntity>lambdaQuery().in(RoleResourcesEntity::getResourcesId, inputDTO.getIds()));
        // 删除资源
        resourcesRepository.deleteByIds(inputDTO.getIds());
        return true;
    }

    @Override
    public Boolean addResources(AddResourcesInputDTO inputDTO) {

        // 判断资源编码是否重复
        if (resourcesRepository.exists(Wrappers.<ResourcesEntity>lambdaQuery().eq(ResourcesEntity::getCode, inputDTO.getCode()))) {
            throw new UserFriendlyException("资源编码已存在,换一个吧", 401);
        }

        // DTO转换实体
        ResourcesEntity resourcesEntity = ModelMapperUtil.map(inputDTO, ResourcesEntity.class);

        // 插入数据库
        resourcesRepository.insert(resourcesEntity);

        return true;
    }

    @Override
    public Page pageQueryList(BaseQueryPageInputDTO inputDTO) {
        return this.resourcesRepository.selectPage(inputDTO.toMybatisPageObject(), Wrappers.<ResourcesEntity>lambdaQuery());
    }

    @Override
    public List<ResourcesTreeVo> resourcesTree() {
        // 查询全部资源列表
        List<ResourcesTreeVo> resourcesVos = resourcesRepository.selectList(null, ResourcesTreeVo.class);
        // 转换ITreeNode List
        List<ITreeNode<Long>> treeNodeList = new ArrayList<>(resourcesVos.size());
        treeNodeList.addAll(resourcesVos);
        // 转换树结构
        List<ITreeNode<Long>> tree = TreeUtil.listToTree(treeNodeList);
        // 转换ResourcesTreeVo List
        return ModelMapperUtil.mapList(tree, ResourcesTreeVo.class);
    }
}
