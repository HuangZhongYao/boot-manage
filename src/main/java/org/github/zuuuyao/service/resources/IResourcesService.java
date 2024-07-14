package org.github.zuuuyao.service.resources;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.service.resources.dto.input.AddResourcesInputDTO;

/**
 * @Desc 用户管理Service
 * @Time 2024-07-11 16:32
 * @Author HuangZhongYao
 */
public interface IResourcesService {


    Boolean delResources(BaseManyLongIdInputDTO inputDTO);

    Boolean addResources(AddResourcesInputDTO inputDTO);

    Page pageQueryList(BaseQueryPageInputDTO inputDTO);

}
