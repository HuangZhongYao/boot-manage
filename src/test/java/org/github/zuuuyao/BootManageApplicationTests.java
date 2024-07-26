package org.github.zuuuyao;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.apache.ibatis.executor.BatchResult;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.common.util.tree.ITreeNode;
import org.github.zuuuyao.common.util.tree.TreeUtil;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.repository.ResourcesRepository;
import org.github.zuuuyao.service.generate.model.ColumnModel;
import org.github.zuuuyao.service.resources.model.ResourcesTreeVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class BootManageApplicationTests {

    @Resource
    ResourcesRepository resourcesRepository;

    public static void main(String[] args) {
        // 创建 Velocity 引擎
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        // 创建十个字段
//        Stream<ColumnModel> columnModels = Stream.generate(() ->
//                ColumnModel
//                        .builder()
//                        .tableName("sys_user")
//                        .columnName("username")
//                        .dataType("varchar")
//                        .columnComment("用户名")
//                        .attrName("username")
//                        .attrType("String")
//                        .build()
//        ).limit(10);

        // 创建列字段
        List<ColumnModel> columns = Arrays.asList(
                ColumnModel
                        .builder()
                        .tableName("sys_user")
                        .columnName("username")
                        .dataType("varchar")
                        .columnComment("用户名")
                        .attrName("username")
                        .attrType("String")
                        .build(),
                ColumnModel
                        .builder()
                        .tableName("sys_user")
                        .columnName("account")
                        .dataType("varchar")
                        .columnComment("登录账号")
                        .attrName("account")
                        .attrType("String")
                        .build(),
                ColumnModel
                        .builder()
                        .tableName("sys_user")
                        .columnName("enable")
                        .dataType("tinyint")
                        .columnComment("启用状态")
                        .attrName("enable")
                        .attrType("Boolean")
                        .build(),
                ColumnModel
                        .builder()
                        .tableName("sys_user")
                        .columnName("created_by")
                        .dataType("bigint")
                        .columnComment("创建人id")
                        .attrName("createdBy")
                        .attrType("Long")
                        .build(),
                ColumnModel
                        .builder()
                        .tableName("sys_user")
                        .columnName("phone")
                        .dataType("varchar")
                        .columnComment("手机号码")
                        .attrName("phone")
                        .attrType("String")
                        .build(),
                ColumnModel
                        .builder()
                        .tableName("sys_user")
                        .columnName("age")
                        .dataType("int")
                        .columnComment("年龄")
                        .attrName("age")
                        .attrType("Integer")
                        .build(),
                ColumnModel
                        .builder()
                        .tableName("sys_user")
                        .columnName("login_time")
                        .dataType("datetime")
                        .columnComment("登录时间")
                        .attrName("loginTime")
                        .attrType("LocalDateTime")
                        .build()
        );

        // 设置模板上下文的变量
        VelocityContext context = new VelocityContext();
        context.put("moduleName", "user");
        context.put("comments", "用户表实体类");
        context.put("author", "zuuuYao");
        context.put("email", "17685306043@163.com");
        context.put("datetime", DateUtil.now());
        context.put("className", "SysUserEntity");
        context.put("tableName", "sys_user");
        context.put("serialVersionUID", System.currentTimeMillis());
        context.put("columns", columns);
        context.put("hasLocalDateTime", true);

        // 获取生成实体模板
//        Template template = velocityEngine.getTemplate("templates/Entity.java.vm");

        Template repostioryTemplate = velocityEngine.getTemplate("templates/Repository.java.vm");
        // 设置模板上下文的变量
        VelocityContext repositoryTemplateContext = new VelocityContext();
        repositoryTemplateContext.put("comments", "用户表仓储层 (DAO层或Mapper层)");
        repositoryTemplateContext.put("author", "zuuuYao");
        repositoryTemplateContext.put("email", "17685306043@163.com");
        repositoryTemplateContext.put("datetime", DateUtil.now());
        repositoryTemplateContext.put("className", "UserRepository");
        repositoryTemplateContext.put("entityName", "UserEntity");
        repositoryTemplateContext.put("entityPackageName", "org.github.zuuuyao.entity.system");


        // 生成代码并输出
//        StringWriter writer = new StringWriter();
//        template.merge(context, writer);
//        System.out.println(writer.toString());


        StringWriter write2 = new StringWriter();
        repostioryTemplate.merge(repositoryTemplateContext,write2);
        System.out.println(write2.toString());
    }

    @Test
    void contextLoads() {
        String josn = """
                [
                    {
                        "id": 9,
                        "name": "基础功能",
                        "code": "Base",
                        "type": "MENU",
                        "parentId": null,
                        "path": "",
                        "redirect": null,
                        "icon": "i-fe:grid",
                        "component": null,
                        "layout": "",
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 0
                    },
                    {
                        "id": 14,
                        "name": "图标 Icon",
                        "code": "Icon",
                        "type": "MENU",
                        "parentId": 9,
                        "path": "/base/icon",
                        "redirect": null,
                        "icon": "i-fe:feather",
                        "component": "/src/views/base/unocss-icon.vue",
                        "layout": "",
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 0
                    },
                    {
                        "id": 10,
                        "name": "基础组件",
                        "code": "BaseComponents",
                        "type": "MENU",
                        "parentId": 9,
                        "path": "/base/components",
                        "redirect": null,
                        "icon": "i-me:awesome",
                        "component": "/src/views/base/index.vue",
                        "layout": null,
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 1
                    },
                    {
                        "id": 11,
                        "name": "Unocss",
                        "code": "Unocss",
                        "type": "MENU",
                        "parentId": 9,
                        "path": "/base/unocss",
                        "redirect": null,
                        "icon": "i-me:awesome",
                        "component": "/src/views/base/unocss.vue",
                        "layout": null,
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 2
                    },
                    {
                        "id": 12,
                        "name": "KeepAlive",
                        "code": "KeepAlive",
                        "type": "MENU",
                        "parentId": 9,
                        "path": "/base/keep-alive",
                        "redirect": null,
                        "icon": "i-me:awesome",
                        "component": "/src/views/base/keep-alive.vue",
                        "layout": null,
                        "keepAlive": true,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 3
                    },
                    {
                        "id": 15,
                        "name": "MeModal",
                        "code": "TestModal",
                        "type": "MENU",
                        "parentId": 9,
                        "path": "/testModal",
                        "redirect": null,
                        "icon": "i-me:dialog",
                        "component": "/src/views/base/test-modal.vue",
                        "layout": null,
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 5
                    },
                    {
                        "id": 6,
                        "name": "业务示例",
                        "code": "Demo",
                        "type": "MENU",
                        "parentId": null,
                        "path": null,
                        "redirect": null,
                        "icon": "i-fe:grid",
                        "component": null,
                        "layout": null,
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 1
                    },
                    {
                        "id": 7,
                        "name": "图片上传",
                        "code": "ImgUpload",
                        "type": "MENU",
                        "parentId": 6,
                        "path": "/demo/upload",
                        "redirect": null,
                        "icon": "i-fe:image",
                        "component": "/src/views/demo/upload/index.vue",
                        "layout": "simple",
                        "keepAlive": true,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 2
                    },
                    {
                        "id": 2,
                        "name": "系统管理",
                        "code": "SysMgt",
                        "type": "MENU",
                        "parentId": null,
                        "path": null,
                        "redirect": null,
                        "icon": "i-fe:grid",
                        "component": null,
                        "layout": null,
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 2
                    },
                    {
                        "id": 1,
                        "name": "资源管理",
                        "code": "Resource_Mgt",
                        "type": "MENU",
                        "parentId": 2,
                        "path": "/pms/resource",
                        "redirect": null,
                        "icon": "i-fe:list",
                        "component": "/src/views/pms/resource/index.vue",
                        "layout": null,
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 1
                    },
                    {
                        "id": 3,
                        "name": "角色管理",
                        "code": "RoleMgt",
                        "type": "MENU",
                        "parentId": 2,
                        "path": "/pms/role",
                        "redirect": null,
                        "icon": "i-fe:user-check",
                        "component": "/src/views/pms/role/index.vue",
                        "layout": null,
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 2
                    },
                    {
                        "id": 5,
                        "name": "分配用户",
                        "code": "RoleUser",
                        "type": "MENU",
                        "parentId": 3,
                        "path": "/pms/role/user/:roleId",
                        "redirect": null,
                        "icon": "i-fe:user-plus",
                        "component": "/src/views/pms/role/role-user.vue",
                        "layout": "full",
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": false,
                        "enable": true,
                        "order": 1
                    },
                    {
                        "id": 4,
                        "name": "用户管理",
                        "code": "UserMgt",
                        "type": "MENU",
                        "parentId": 2,
                        "path": "/pms/user",
                        "redirect": null,
                        "icon": "i-fe:user",
                        "component": "/src/views/pms/user/index.vue",
                        "layout": null,
                        "keepAlive": true,
                        "method": null,
                        "description": null,
                        "show": true,
                        "enable": true,
                        "order": 3
                    },
                    {
                        "id": 8,
                        "name": "个人资料",
                        "code": "UserProfile",
                        "type": "MENU",
                        "parentId": null,
                        "path": "/profile",
                        "redirect": null,
                        "icon": "i-fe:user",
                        "component": "/src/views/profile/index.vue",
                        "layout": null,
                        "keepAlive": null,
                        "method": null,
                        "description": null,
                        "show": false,
                        "enable": true,
                        "order": 99
                    }
                ]
                                """;

        JSONArray entries = JSONUtil.parseArray(josn);
        List<ResourcesEntity> resourcesEntities =
                ModelMapperUtil.mapList(entries, ResourcesEntity.class, (source, target) -> {
                    target.setIsShow(((JSONObject) source).getBool("show"));
                    target.setSort(((JSONObject) source).getStr("order"));
                });

        List<BatchResult> insert =
                resourcesRepository.insert(resourcesEntities, resourcesEntities.size());
        System.out.println("data = " + entries);
    }


    /**
     * 测试树工具类 list数据转树结构
     */
    @Test
    public void testTreeUtil() {

        List<ResourcesTreeVo> resourcesTreeVos =
                resourcesRepository.selectList(new QueryWrapper<>(), ResourcesTreeVo.class);


        List<ITreeNode<Long>> resourcesEntities = new ArrayList<>();
        resourcesEntities.addAll(resourcesTreeVos);

        List<ITreeNode<Long>> iTreeNodes = TreeUtil.listToTree(resourcesEntities);
        System.out.println();
    }
}
