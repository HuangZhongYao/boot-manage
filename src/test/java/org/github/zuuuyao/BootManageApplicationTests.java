package org.github.zuuuyao;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.apache.ibatis.executor.BatchResult;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.repository.ResourcesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BootManageApplicationTests {

    @Resource
    ResourcesRepository resourcesRepository;

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
        List<ResourcesEntity> resourcesEntities = ModelMapperUtil.mapList(entries, ResourcesEntity.class, (source, target) -> {
            target.setIsShow(((JSONObject) source).getBool("show"));
            target.setSort(((JSONObject) source).getStr("order"));
        });

        List<BatchResult> insert = resourcesRepository.insert(resourcesEntities, resourcesEntities.size());
        System.out.println("data = " + entries);
    }

}
