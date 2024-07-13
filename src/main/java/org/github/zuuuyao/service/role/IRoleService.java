package org.github.zuuuyao.service.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.service.role.dto.input.AddRoleInputDTO;

/**
 * @Desc 角色管理 Service
 * @Time 2024-07-12 16:49
 * @Author HuangZhongYao
 */
public interface IRoleService {
    boolean addRole(AddRoleInputDTO inputDTO);

    Boolean delRole(BaseManyLongIdInputDTO inputDTO);

    Page pageQueryList(BaseQueryPageInputDTO inputDTO);
}
