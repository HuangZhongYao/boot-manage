package org.github.zuuuyao.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.github.zuuuyao.entity.enums.GenderEnum;
import org.github.zuuuyao.entity.system.UserEntity;
import org.github.zuuuyao.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * @Desc
 * @Time 2024-07-11 16:32
 * @Author HuangZhongYao
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserRepository userRepository;

    @GetMapping("/list")
    public Object list(){
        UserEntity userEntity = UserEntity.builder().account("788fsd").username("dhfisdhi").password("0090").salt("dfsdfsdfd").gender(GenderEnum.FEMALE).build();
        userRepository.insert(userEntity);
        return userRepository.selectPage(new Page<>(),new QueryWrapper<UserEntity>().lambda());
    }
}
