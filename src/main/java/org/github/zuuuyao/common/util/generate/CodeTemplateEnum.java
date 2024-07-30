package org.github.zuuuyao.common.util.generate;

/**
 * 枚举类CodeTemplateEnum定义了代码生成过程中使用的各种模板类型。
 * 每个枚举值代表了一种特定的代码模板，用于生成对应的代码文件，如实体类、数据传输对象、服务接口等。
 * 这些模板类型为代码生成工具提供了标准化的分类，便于根据不同的需求生成相应的代码片段。
 *
 * @Desc 代码模板枚举
 * @Time 2024-07-30 14:23
 * @Author HuangZhongYao
 */
public enum CodeTemplateEnum {
    // 实体类模板，用于生成POJO类
    Entity(),
    // 实体视图对象模板，用于生成用于展示的VO类
    EntityVO(),
    // 添加操作的数据传输对象模板，用于封装添加操作所需的参数
    AddInputDTO(),
    // 修改操作的数据传输对象模板，用于封装修改操作所需的参数
    EditInputDTO(),
    // 查询分页输入对象模板，用于封装分页查询操作所需的参数
    QueryPageInputDTO(),
    // 数据访问层接口模板，用于生成Repository接口
    Repository(),
    // 数据访问层映射器接口模板，用于生成Mapper接口
    Mapper(),
    // 业务服务接口模板，用于生成Service接口
    Service(),
    // 业务服务实现类模板，用于生成ServiceImpl类
    ServiceImpl(),
    // 控制器层模板，用于生成Controller类
    Controller(),
    // API接口模板，用于生成API接口
    Api(),
    // vue页面模板，用于生成应用或模块的首页
    Index(),
    ;

}
