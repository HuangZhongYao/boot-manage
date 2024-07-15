package org.github.zuuuyao.service.auth;

import org.github.zuuuyao.service.auth.model.ResourcesTreeVo;
import org.github.zuuuyao.service.auth.model.ResourcesVo;

import java.util.List;

/**
 * @Desc
 * @Time 2024-07-15 15:02
 * @Author HuangZhongYao
 */
public interface IAuthService {

    List<ResourcesVo> queryPermissionsList();

    List<ResourcesTreeVo> queryPermissionsTree();

}
