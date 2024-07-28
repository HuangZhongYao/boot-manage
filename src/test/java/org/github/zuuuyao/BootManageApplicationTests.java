package org.github.zuuuyao;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.CharSet;
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

@SpringBootTest
class BootManageApplicationTests {

    @Resource
    ResourcesRepository resourcesRepository;

    public static void main(String[] args) throws IOException {
        testTemplateBackEndCode();
    }

    public static void testTemplateBackEndCode() throws IOException {

        // 创建 Velocity 引擎
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class",
            ClasspathResourceLoader.class.getName());

        Template entityTemplate = velocityEngine.getTemplate("templates/Entity.java.vm", CharsetUtil.UTF_8);
        Template addInputDTOTemplate = velocityEngine.getTemplate("templates/AddInputDTO.java.vm", CharsetUtil.UTF_8);
        Template editInputDTOTemplate = velocityEngine.getTemplate("templates/EditInputDTO.java.vm", CharsetUtil.UTF_8);
        Template queryPageInputDTOTemplate = velocityEngine.getTemplate("templates/QueryPageInputDTO.java.vm", CharsetUtil.UTF_8);
        Template entityVOTemplate = velocityEngine.getTemplate("templates/EntityVO.java.vm", CharsetUtil.UTF_8);
        Template repositoryTemplate = velocityEngine.getTemplate("templates/Repository.java.vm", CharsetUtil.UTF_8);
        Template mapperTemplate = velocityEngine.getTemplate("templates/Mapper.xml.vm", CharsetUtil.UTF_8);
        Template serviceTemplate = velocityEngine.getTemplate("templates/Service.java.vm", CharsetUtil.UTF_8);
        Template serviceImplTemplate = velocityEngine.getTemplate("templates/ServiceImpl.java.vm", CharsetUtil.UTF_8);
        Template controllerlTemplate = velocityEngine.getTemplate("templates/Controller.java.vm", CharsetUtil.UTF_8);


        StringWriter writer = new StringWriter();


        String tabName = "bus_order";
        String tabComments = "订单表";
        String moduleName = "order";
        String author = "zuuuYao";
        String dateTime = DateUtil.now();
        String basePackage = "org.github.zuuuyao";
        String javaCodePath = "src" + File.separator + "main" + File.separator + "java" + File.separator;
        String mapperXMLPath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper" + File.separator;
        String vueCodePath = "views"+ File.separator;

        boolean hasLocalDateTime = true;


        String servicePackageName = basePackage + ".service." + moduleName;
        String serviceImplPackageName = basePackage + ".service." + moduleName + ".impl";
        String inputDTOPackageName = servicePackageName + ".input";
        String outputDTOPackageName = servicePackageName + ".output";

        // 创建列字段
        List<ColumnModel> columns = Arrays.asList(
            ColumnModel
                .builder()
                .tableName(tabName)
                .columnName("order_no")
                .dataType("varchar")
                .columnComment("订单号")
                .attrName("orderNo")
                .attrType("String")
                .build(),
            ColumnModel
                .builder()
                .tableName(tabName)
                .columnName("commodity")
                .dataType("varchar")
                .columnComment("商品")
                .attrName("commodity")
                .attrType("String")
                .build(),
            ColumnModel
                .builder()
                .tableName(tabName)
                .columnName("order_time")
                .dataType("datetime")
                .columnComment("下单时间")
                .attrName("orderTime")
                .attrType("LocalDateTime")
                .build(),
            ColumnModel
                .builder()
                .tableName(tabName)
                .columnName("address")
                .dataType("varchar")
                .columnComment("收货地址")
                .attrName("address")
                .attrType("String")
                .build(),
            ColumnModel
                .builder()
                .tableName(tabName)
                .columnName("consignee")
                .dataType("varchar")
                .columnComment("收货人")
                .attrName("consignee")
                .attrType("String")
                .build(),
            ColumnModel
                .builder()
                .tableName(tabName)
                .columnName("consignee_phone")
                .dataType("varchar")
                .columnComment("收货人电话")
                .attrName("consigneePhone")
                .attrType("String")
                .build(),
            ColumnModel
                .builder()
                .tableName(tabName)
                .columnName("pay_state")
                .dataType("tinyint")
                .columnComment("支付状态")
                .attrName("payState")
                .attrType("Integer")
                .build()
        );

        //
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        // 实体
        String entityClassName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Entity";
        String domainName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName));
        String entityPackageName = basePackage  + ".entity" + "." + moduleName;
        String entityFullName = entityPackageName + "." + entityClassName;
        String entityComments = tabComments + "实体";


        VelocityContext entityContext = new VelocityContext();
        entityContext.put("packageName", entityPackageName);
        entityContext.put("comments", entityComments);
        entityContext.put("author", author);
        entityContext.put("datetime", dateTime);
        entityContext.put("className", entityClassName);
        entityContext.put("tableName", tabName);
        entityContext.put("columns", columns);
        entityContext.put("hasLocalDateTime", true);

        writer.getBuffer().setLength(0);
        entityTemplate.merge(entityContext, writer);
        System.out.println();
        System.out.println("Entity==================================================\n\n" + writer);
        //添加到zip
        zip.putNextEntry(new ZipEntry(
            getFileName(entityTemplate.getName(),javaCodePath+entityFullName)
        ));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();

        // entityVO
        String entityVOName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "VO";
        String entityVOComments = tabComments + "VO对象";
        String entityVOFullName = outputDTOPackageName + "." + entityVOName;

        VelocityContext entityVOContext = new VelocityContext();
        entityVOContext.put("packageName", outputDTOPackageName);
        entityVOContext.put("comments", entityVOComments);
        entityVOContext.put("author", author);
        entityVOContext.put("datetime", dateTime);
        entityVOContext.put("className", entityVOName);
        entityVOContext.put("tableName", tabName);
        entityVOContext.put("columns", columns);
        entityVOContext.put("hasLocalDateTime", true);

        writer.getBuffer().setLength(0);
        entityVOTemplate.merge(entityVOContext, writer);
        System.out.println();
        System.out.println(
            "EntityVO==================================================\n\n" + writer);
        //添加到zip
        zip.putNextEntry(new ZipEntry(
            getFileName(entityVOTemplate.getName(),javaCodePath+entityVOFullName)
        ));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();



        // AddInputDTO
        String addInputDTOName =
            "Add" + StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "InputDTO";
        String addInputDTOComments = tabComments + "新增数据DTO对象";
        String addInputDTOFullName = inputDTOPackageName + "." + addInputDTOName;

        VelocityContext addInputDTOContext = new VelocityContext();
        addInputDTOContext.put("packageName", inputDTOPackageName);
        addInputDTOContext.put("comments", addInputDTOComments);
        addInputDTOContext.put("author", author);
        addInputDTOContext.put("datetime", dateTime);
        addInputDTOContext.put("className", addInputDTOName);
        addInputDTOContext.put("tableName", tabName);
        addInputDTOContext.put("columns", columns);
        addInputDTOContext.put("hasLocalDateTime", true);

        writer.getBuffer().setLength(0);
        addInputDTOTemplate.merge(addInputDTOContext, writer);
        System.out.println();
        System.out.println(
            "AddInputDTO==================================================\n\n" + writer);
        //添加到zip
        zip.putNextEntry(new ZipEntry(
            getFileName(addInputDTOTemplate.getName(),javaCodePath+addInputDTOFullName)
        ));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();


        // EditInputDTO
        String editInputDTOName =
            "Edit" + StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "InputDTO";
        String editInputDTOComments = tabComments + "编辑数据DTO对象";
        String editInputDTOFullName = inputDTOPackageName + "." + editInputDTOName;

        VelocityContext editInputDTOContext = new VelocityContext();
        editInputDTOContext.put("packageName", inputDTOPackageName);
        editInputDTOContext.put("comments", editInputDTOComments);
        editInputDTOContext.put("author", author);
        editInputDTOContext.put("datetime", dateTime);
        editInputDTOContext.put("className", editInputDTOName);
        editInputDTOContext.put("tableName", tabName);
        editInputDTOContext.put("columns", columns);
        editInputDTOContext.put("hasLocalDateTime", true);

        writer.getBuffer().setLength(0);
        editInputDTOTemplate.merge(editInputDTOContext, writer);
        System.out.println();
        System.out.println(
            "EditInputDTO==================================================\n\n" + writer);
        //添加到zip
        zip.putNextEntry(new ZipEntry(
            getFileName(editInputDTOTemplate.getName(),javaCodePath+editInputDTOFullName)
        ));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();


        // QueryPageInputDTO
        String queryPageInputDTOName =
            StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "QueryPageInputDTO";
        String queryPageInputDTOComments = tabComments + "分页查询DTO对象";
        String queryPageInputDTOFullName = inputDTOPackageName + "." + queryPageInputDTOName;

        VelocityContext queryPageInputDTOContext = new VelocityContext();
        queryPageInputDTOContext.put("packageName", inputDTOPackageName);
        queryPageInputDTOContext.put("comments", queryPageInputDTOComments);
        queryPageInputDTOContext.put("author", author);
        queryPageInputDTOContext.put("datetime", dateTime);
        queryPageInputDTOContext.put("className", queryPageInputDTOName);
        queryPageInputDTOContext.put("tableName", tabName);
        queryPageInputDTOContext.put("columns", columns);
        queryPageInputDTOContext.put("hasLocalDateTime", true);

        writer.getBuffer().setLength(0);
        queryPageInputDTOTemplate.merge(queryPageInputDTOContext, writer);
        System.out.println();
        System.out.println(
            "QueryPageInputDTO==================================================\n\n" + writer);
        //添加到zip
        zip.putNextEntry(new ZipEntry(
            getFileName(queryPageInputDTOTemplate.getName(),javaCodePath+queryPageInputDTOFullName)
        ));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();


        // Repository
        String repositoryName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Repository";
        String repositoryFullName = basePackage + ".repository." + StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Repository";
        String repositoryComments = tabComments + "仓储层";
        VelocityContext repositoryContext = new VelocityContext();
        repositoryContext.put("entityFullName", entityFullName);
        repositoryContext.put("entityName", entityClassName);
        repositoryContext.put("comments", repositoryComments);
        repositoryContext.put("author", author);
        repositoryContext.put("datetime", dateTime);
        repositoryContext.put("className", repositoryName);
        repositoryContext.put("tableName", tabName);
        repositoryContext.put("columns", columns);
        repositoryContext.put("hasLocalDateTime", true);

        writer.getBuffer().setLength(0);
        repositoryTemplate.merge(repositoryContext, writer);
        System.out.println();
        System.out.println(
            "Repository==================================================\n\n" + writer);
        //添加到zip
        zip.putNextEntry(new ZipEntry(
            getFileName(repositoryTemplate.getName(),javaCodePath+repositoryFullName)
        ));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();


        // Mapper
        String mapperName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Mapper";
        VelocityContext mapperContext = new VelocityContext();
        mapperContext.put("irepository", repositoryName);

        writer.getBuffer().setLength(0);
        mapperTemplate.merge(mapperContext, writer);
        System.out.println();
        System.out.println("Mapper==================================================\n\n" + writer);
        //添加到zip
        zip.putNextEntry(new ZipEntry(
            getFileName(mapperTemplate.getName(),mapperXMLPath+mapperName)
        ));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();

        // Service

        String serviceName = "I" + StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Service";
        String serviceFullName = servicePackageName + "." + serviceName;
        String serviceComments = tabComments + "Service服务接口层";

        VelocityContext serviceContext = new VelocityContext();
        serviceContext.put("packageName", servicePackageName);
        serviceContext.put("comments", serviceComments);
        serviceContext.put("author", author);
        serviceContext.put("datetime", dateTime);
        serviceContext.put("className", serviceName);
        serviceContext.put("addInputDTOPackageName", inputDTOPackageName);
        serviceContext.put("addInputDTO", addInputDTOName);
        serviceContext.put("editInputDTOPackageName", inputDTOPackageName);
        serviceContext.put("editInputDTO", editInputDTOName);
        serviceContext.put("queryPageInputDTOPackageName", inputDTOPackageName);
        serviceContext.put("queryPageInputDTO", queryPageInputDTOName);
        serviceContext.put("entityVOPackageName", outputDTOPackageName);
        serviceContext.put("entityVO", entityVOName);
        serviceContext.put("domainName", domainName);

        writer.getBuffer().setLength(0);
        serviceTemplate.merge(serviceContext, writer);
        System.out.println();
        System.out.println(
            "Service==================================================\n\n" + writer);
        //添加到zip
        zip.putNextEntry(new ZipEntry(
            getFileName(serviceTemplate.getName(),javaCodePath+serviceFullName)
        ));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();


        // ServiceImpl

        String serviceImplName =
            "I" + StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "ServiceImpl";
        String serviceImplFullName = serviceImplPackageName + "." + serviceImplName;
        String serviceImplComments = tabComments + "Service服务接口实现层";

        VelocityContext serviceImplContext = new VelocityContext();
        serviceImplContext.put("packageName", serviceImplPackageName);
        serviceImplContext.put("comments", serviceImplComments);
        serviceImplContext.put("author", author);
        serviceImplContext.put("datetime", dateTime);
        serviceImplContext.put("className", serviceImplName);
        serviceImplContext.put("addInputDTOPackageName", inputDTOPackageName);
        serviceImplContext.put("addInputDTO", addInputDTOName);
        serviceImplContext.put("editInputDTOPackageName", inputDTOPackageName);
        serviceImplContext.put("editInputDTO", editInputDTOName);
        serviceImplContext.put("queryPageInputDTOPackageName", inputDTOPackageName);
        serviceImplContext.put("queryPageInputDTO", queryPageInputDTOName);
        serviceImplContext.put("entityVOPackageName", outputDTOPackageName);
        serviceImplContext.put("entityVO", entityVOName);
        serviceImplContext.put("irepository", repositoryName);
        serviceImplContext.put("repository", StrUtil.lowerFirst(repositoryName));
        serviceImplContext.put("servicePackageName", servicePackageName);
        serviceImplContext.put("iservice", serviceName);
        serviceImplContext.put("entityPackageName", "org.github.zuuuyao.entity." + moduleName);
        serviceImplContext.put("entityName", entityClassName);
        serviceImplContext.put("domainName", domainName);

        writer.getBuffer().setLength(0);
        serviceImplTemplate.merge(serviceImplContext, writer);
        System.out.println();
        System.out.println(
            "ServiceImpl==================================================\n\n" + writer);

        //添加到zip
        zip.putNextEntry(new ZipEntry(
            getFileName(serviceImplTemplate.getName(),javaCodePath+serviceImplFullName)
        ));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();

        // Controller

        String controllerName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Controller";
        String controllerFullName = basePackage + ".web." + controllerName;
        String controllerComments = tabComments + "Controller层,接受处理请求";

        VelocityContext controllerContext = new VelocityContext();
        controllerContext.put("comments", controllerComments);
        controllerContext.put("author", author);
        controllerContext.put("datetime", dateTime);
        controllerContext.put("className", controllerName);
        controllerContext.put("addInputDTOPackageName", inputDTOPackageName);
        controllerContext.put("addInputDTO", addInputDTOName);
        controllerContext.put("editInputDTOPackageName", inputDTOPackageName);
        controllerContext.put("editInputDTO", editInputDTOName);
        controllerContext.put("queryPageInputDTOPackageName", inputDTOPackageName);
        controllerContext.put("queryPageInputDTO", queryPageInputDTOName);
        controllerContext.put("entityVOPackageName", outputDTOPackageName);
        controllerContext.put("entityVO", entityVOName);
        controllerContext.put("irepository", repositoryName);
        controllerContext.put("repository", StrUtil.lowerFirst(repositoryName));
        controllerContext.put("servicePackageName", servicePackageName);
        controllerContext.put("iservice", serviceName);
        controllerContext.put("service", StrUtil.toCamelCase(tabName) + "Service");
        controllerContext.put("requestMapping", "/" + moduleName);
        controllerContext.put("tab", tabComments.replaceAll("表", ""));
        controllerContext.put("tagName", tabComments.replaceAll("表", "") + "接口");
        controllerContext.put("domainName", domainName);

        writer.getBuffer().setLength(0);
        controllerlTemplate.merge(controllerContext, writer);
        System.out.println();
        System.out.println(
            "Controller==================================================\n\n" + writer);
        //添加到zip
        zip.putNextEntry(new ZipEntry(
            getFileName(controllerlTemplate.getName(),javaCodePath+controllerFullName)
        ));
        IOUtils.write(writer.toString(), zip, "UTF-8" );
        IOUtils.closeQuietly(writer);
        zip.closeEntry();

        IOUtils.closeQuietly(zip);

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\"+moduleName+"_code.zip");
        outputStream.writeTo(fileOutputStream);

    }

    public  static String getFileName(String templateName,String path){

        String filePath = path.replaceAll("\\.", Matcher.quoteReplacement(File.separator));

        if (templateName.contains("java")) {
            return filePath + ".java";
        }
        if (templateName.contains("xml")) {
            return filePath + ".xml";
        }
        if (templateName.contains("vue")) {
            return filePath + ".vue";
        }
        if (templateName.contains("js")) {
            return filePath + ".js";
        }

        return filePath + "";
    }


    public static void testVol() {
        // 创建 Velocity 引擎
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class",
            ClasspathResourceLoader.class.getName());

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
        repostioryTemplate.merge(repositoryTemplateContext, write2);
        System.out.println(write2.toString());


        Template addtemplate = velocityEngine.getTemplate("templates/AddInputDTO.java.vm");
        // 设置模板上下文的变量
        VelocityContext addtemplateContext = new VelocityContext();
        addtemplateContext.put("comments", "添加用户DTO");
        addtemplateContext.put("author", "zuuuYao");
        addtemplateContext.put("email", "17685306043@163.com");
        addtemplateContext.put("datetime", DateUtil.now());
        addtemplateContext.put("className", "AddUserInputDTO");
        addtemplateContext.put("packageName", "org.github.zuuuyao.service.user.dto.input");
        addtemplateContext.put("columns", columns);

        StringWriter write4 = new StringWriter();
        addtemplate.merge(addtemplateContext, write4);

        System.out.println("write4 = " + write4);
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
