package org.github.zuuuyao.service.resources.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.repository.ResourcesRepository;
import org.github.zuuuyao.service.resources.IResourcesService;
import org.github.zuuuyao.service.resources.dto.input.AddResourcesInputDTO;
import org.springframework.stereotype.Service;

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

    @Override
    public Boolean delResources(BaseManyLongIdInputDTO inputDTO) {
        resourcesRepository.deleteByIds(inputDTO.getIds());
        return true;
    }

    @Override
    public Boolean addResources(AddResourcesInputDTO inputDTO) {

        // 判断资源编码是否重复
        if (resourcesRepository.exists(
            Wrappers.<ResourcesEntity>lambdaQuery()
                .eq(ResourcesEntity::getCode, inputDTO.getCode()))) {
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
        return this.resourcesRepository.selectPage(inputDTO.toMybatisPageObject(),
            Wrappers.<ResourcesEntity>lambdaQuery());
    }
}
