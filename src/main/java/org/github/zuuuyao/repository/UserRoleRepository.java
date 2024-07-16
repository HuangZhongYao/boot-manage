package org.github.zuuuyao.repository;

import org.github.zuuuyao.config.mybatis.extension.BaseMapperExtension;
import org.github.zuuuyao.entity.system.UserEntity;
import org.github.zuuuyao.entity.system.UserRoleEntity;
import org.springframework.stereotype.Repository;

/**
 * 用户角色表数据库访问对象
 * @Desc 用户角色表仓储层
 * @Time 2024-07-11 16:32
 * @Author HuangZhongYao
 */
@Repository
public interface UserRoleRepository extends BaseMapperExtension<UserRoleEntity> {
}
