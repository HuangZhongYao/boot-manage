package org.github.zuuuyao.service.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.entity.system.RoleEntity;
import org.github.zuuuyao.service.role.dto.input.AddRoleInputDTO;
import org.github.zuuuyao.service.role.dto.input.EditRoleInputDTO;
import org.github.zuuuyao.service.role.dto.input.RolePageQueryInputDTO;
import org.github.zuuuyao.service.role.dto.output.RoleVo;

import java.util.List;

/**
 * @Desc 角色管理 Service
 * @Time 2024-07-12 16:49
 * @Author HuangZhongYao
 */
public interface IRoleService {
    Boolean addRole(AddRoleInputDTO inputDTO);

    Boolean delRole(BaseManyLongIdInputDTO inputDTO);

    Page<RoleVo> pageQueryList(RolePageQueryInputDTO inputDTO);

    List<RoleVo> queryList(Boolean enable);

    Boolean editRole(EditRoleInputDTO inputDTO);
}
