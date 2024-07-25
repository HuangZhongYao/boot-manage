package org.github.zuuuyao.service.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.service.role.dto.input.AddRoleInputDTO;
import org.github.zuuuyao.service.role.dto.input.EditRoleInputDTO;
import org.github.zuuuyao.service.role.dto.input.RolePageQueryInputDTO;
import org.github.zuuuyao.service.role.dto.input.SetRoleStateInputDTO;
import org.github.zuuuyao.service.role.dto.input.SetRoleUserInputDTO;
import org.github.zuuuyao.service.role.dto.model.RoleUserModel;
import org.github.zuuuyao.service.role.dto.output.RolePageQueryListItemVo;
import org.github.zuuuyao.service.role.dto.output.RoleVo;

/**
 * @Desc 角色管理 Service
 * @Time 2024-07-12 16:49
 * @Author HuangZhongYao
 */
public interface IRoleService {
    Boolean addRole(AddRoleInputDTO inputDTO);

    Boolean delRole(BaseManyLongIdInputDTO inputDTO);

    Page<RolePageQueryListItemVo> pageQueryList(RolePageQueryInputDTO inputDTO);

    List<RoleVo> queryList(Boolean enable);

    Boolean editRole(EditRoleInputDTO inputDTO);

    List<RoleUserModel> queryRoleUserList(Long id);

    /**
     * 批量给角色设置用户
     * @param inputDTO SetRoleUserInputDTO
     * @return 成功true
     */
    Boolean setRoleUser(SetRoleUserInputDTO inputDTO);

    /**
     * 设置角色启用状态
     * @param inputDTO
     * @return
     */
    Boolean setState(SetRoleStateInputDTO inputDTO);

}
