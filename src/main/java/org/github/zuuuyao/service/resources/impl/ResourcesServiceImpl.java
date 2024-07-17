package org.github.zuuuyao.service.resources.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.common.util.tree.ITreeNode;
import org.github.zuuuyao.common.util.tree.TreeUtil;
import org.github.zuuuyao.entity.enums.ResourcesTypeEnum;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.entity.system.RoleResourcesEntity;
import org.github.zuuuyao.repository.ResourcesRepository;
import org.github.zuuuyao.repository.RoleResourcesRepository;
import org.github.zuuuyao.service.resources.IResourcesService;
import org.github.zuuuyao.service.resources.dto.input.AddResourcesInputDTO;
import org.github.zuuuyao.service.resources.dto.input.EditResourcesInputDTO;
import org.github.zuuuyao.service.resources.model.ResourcesTreeVo;
import org.github.zuuuyao.service.resources.model.ResourcesVo;
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
            throw new UserFriendlyException("资源编码已存在,换一个吧");
        }

        // DTO转换实体
        ResourcesEntity resourcesEntity = ModelMapperUtil.map(inputDTO, ResourcesEntity.class, (source, target) -> {
            target.setSort(source.getOrder());
            target.setIsShow(source.getShow());
        });

        // 插入数据库
        resourcesRepository.insert(resourcesEntity);

        return true;
    }

    @Override
    public Boolean editResources(EditResourcesInputDTO inputDTO) {

        // 数据库中的数据
        ResourcesEntity resourcesEntity = resourcesRepository.selectById(inputDTO.getId());

        // 修改了资源编码需要判断是否重复
        if (!resourcesEntity.getCode().equals(inputDTO.getCode())) {
            // 判断资源编码是否重复
            if (resourcesRepository.exists(Wrappers.<ResourcesEntity>lambdaQuery()
                    .ne(ResourcesEntity::getCode, resourcesEntity.getCode())
                    .eq(ResourcesEntity::getCode, inputDTO.getCode()))) {

                throw new UserFriendlyException("资源编码已存在,换一个吧!");
            }
        }

        // DTO转换实体
        ResourcesEntity updateEntity = ModelMapperUtil.map(inputDTO, ResourcesEntity.class, (source, target) -> {
            target.setSort(source.getOrder());
            target.setIsShow(source.getShow());
        });

        // 执行更新
        this.resourcesRepository.updateById(updateEntity);

        return true;
    }

    @Override
    public List<ResourcesVo> button(Long parentId) {
        // 查询条件
        LambdaQueryWrapper<ResourcesEntity> queryWrapper = Wrappers.<ResourcesEntity>lambdaQuery()
                .eq(ResourcesEntity::getParentId, parentId)
                .eq(ResourcesEntity::getType, ResourcesTypeEnum.BUTTON)
                .orderByAsc(ResourcesEntity::getSort);
        // 执行查询转换类型
        return this.resourcesRepository.selectList(queryWrapper, ResourcesVo.class);
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
