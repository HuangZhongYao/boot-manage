package org.github.zuuuyao.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.github.zuuuyao.config.mybatis.extension.BaseMapperExtension;
import org.github.zuuuyao.entity.system.RoleEntity;
import org.github.zuuuyao.service.role.dto.input.RolePageQueryInputDTO;
import org.github.zuuuyao.service.role.dto.output.RolePageQueryListItemVo;
import org.springframework.stereotype.Repository;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-13 18:39
 */
@Repository
public interface RoleRepository extends BaseMapperExtension<RoleEntity> {

    /**
     * 分页查询
     *
     * @param page 分页插件参数
     * @param inputDTO          查询参数
     * @return 分页结果
     */
    Page<RolePageQueryListItemVo> pageQueryList(@Param("page") Page page, @Param("param") RolePageQueryInputDTO inputDTO);
}
