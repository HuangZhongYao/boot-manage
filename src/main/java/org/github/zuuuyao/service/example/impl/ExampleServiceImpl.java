package org.github.zuuuyao.service.example.impl;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.service.example.IExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-13 17:09
 */
@Service
// @AllArgsConstructor 加了全参构造函数注解可以省掉 @Autowired、@Resource来注入所需要的bean
@AllArgsConstructor
public class ExampleServiceImpl implements IExampleService {
}
