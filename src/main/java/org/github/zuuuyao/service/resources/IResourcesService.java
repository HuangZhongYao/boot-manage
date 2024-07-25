package org.github.zuuuyao.service.resources;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.service.resources.dto.input.AddResourcesInputDTO;
import org.github.zuuuyao.service.resources.dto.input.EditResourcesInputDTO;
import org.github.zuuuyao.service.resources.dto.input.SetResourcesStateInputDTO;
import org.github.zuuuyao.service.resources.model.ResourcesTreeVo;
import org.github.zuuuyao.service.resources.model.ResourcesVo;

import java.util.List;

/**
 * @Desc 用户管理Service
 * @Time 2024-07-11 16:32
 * @Author HuangZhongYao
 */
public interface IResourcesService {


    Boolean delResources(BaseManyLongIdInputDTO inputDTO);

    Boolean addResources(AddResourcesInputDTO inputDTO);

    List<ResourcesTreeVo> resourcesTree();

    /**
     * 查询资源下的按钮
     * @param parentId 资源id
     * @return 下级按钮集合
     */
    List<ResourcesVo> button(Long parentId);

    Boolean editResources(EditResourcesInputDTO inputDTO);

    Boolean setState(SetResourcesStateInputDTO inputDTO);
}
