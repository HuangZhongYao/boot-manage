package org.github.zuuuyao.service.user;

import org.github.zuuuyao.common.base.dto.input.BaseQueryPageInputDTO;
import org.github.zuuuyao.service.user.dto.input.AddUserInputDTO;

/**
 * @Desc
 * @Time 2024-07-11 16:32
 * @Author HuangZhongYao
 */
public interface IUserService {

    Object addUser(AddUserInputDTO inputDTO);

    Object pageQueryList(BaseQueryPageInputDTO inputDTO);

}
