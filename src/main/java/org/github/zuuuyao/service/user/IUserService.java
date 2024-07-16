package org.github.zuuuyao.service.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.github.zuuuyao.common.base.dto.input.BaseManyLongIdInputDTO;
import org.github.zuuuyao.service.user.dto.input.AddUserInputDTO;
import org.github.zuuuyao.service.user.dto.input.EditUserInputDTO;
import org.github.zuuuyao.service.user.dto.input.ResetPasswordInputDTO;
import org.github.zuuuyao.service.user.dto.input.SetRoleInputDTO;
import org.github.zuuuyao.service.user.dto.input.UserQueryPageInputDTO;
import org.github.zuuuyao.service.user.dto.output.UserVo;

/**
 * @Desc 用户管理Service
 * @Time 2024-07-11 16:32
 * @Author HuangZhongYao
 */
public interface IUserService {

    Boolean addUser(AddUserInputDTO inputDTO);

    Page<UserVo> pageQueryList(UserQueryPageInputDTO inputDTO);

    Boolean delUser(BaseManyLongIdInputDTO inputDTO);

    Boolean resetPassword(ResetPasswordInputDTO inputDTO);

    Boolean setRole(SetRoleInputDTO inputDTO);

    Boolean editUser(EditUserInputDTO inputDTO);
}
