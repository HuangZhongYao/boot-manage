package org.github.zuuuyao.service.generate.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.DB;
import org.github.zuuuyao.common.util.ModelMapperUtil;
import org.github.zuuuyao.common.util.generate.CodeGenerationUtil;
import org.github.zuuuyao.common.util.generate.CodeTemplateEnum;
import org.github.zuuuyao.entity.enums.ResourcesTypeEnum;
import org.github.zuuuyao.entity.system.ResourcesEntity;
import org.github.zuuuyao.repository.GenerateRepository;
import org.github.zuuuyao.repository.ResourcesRepository;
import org.github.zuuuyao.service.generate.IGenerateService;
import org.github.zuuuyao.service.generate.dto.input.CodeGenerateInputDTO;
import org.github.zuuuyao.service.generate.dto.output.ColumnVO;
import org.github.zuuuyao.service.generate.dto.output.TableVO;
import org.github.zuuuyao.service.generate.model.ColumnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成服务类
 *
 * @Desc
 * @Time 2024-07-29 10:01
 * @Author HuangZhongYao
 */
@Service
@AllArgsConstructor
public class GenerateServiceImpl implements IGenerateService {

    private static final Logger log = LoggerFactory.getLogger(GenerateServiceImpl.class);
    GenerateRepository generateRepository;
    ResourcesRepository resourcesRepository;

    @Override
    public List<TableVO> getTables() {
        return ModelMapperUtil.mapList(generateRepository.getTables(), TableVO.class);
    }

    @Override
    public List<ColumnVO> getTableColumns(String tableName) {
        return ModelMapperUtil.mapList(generateRepository.getTableColumns(tableName), ColumnVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<byte[]> codeGeneration(CodeGenerateInputDTO inputDTO, HttpServletResponse response) {
        try {

            // 因为生成的实体要继承AbstractBaseEntity 已经继承了这些基础属性。所以排除掉
            List<String> excludeFields = Arrays.asList(
                    "id", "created_time", "updated_time", "created_by", "updated_by"
            );

            // 字段信息
            List<ColumnModel> columns = inputDTO.getColumns();
            // 迭代器遍历
            Iterator<ColumnModel> iterator = columns.iterator();
            while (iterator.hasNext()) {

                ColumnModel column = iterator.next();

                // 排除继承字段
                if (excludeFields.contains(column.getColumnName())) {
                    iterator.remove();
                    continue;
                }

                // 检查字段信息
                if (StrUtil.isBlank(column.getColumnComment())) {
                    throw new UserFriendlyException(column.getColumnName() + "字段的字段备注不能为空");
                }
                if (StrUtil.isBlank(column.getDataType())) {
                    throw new UserFriendlyException(column.getColumnName() + "字段的数据类型不能为空");
                }

                // 转换属性名和属性类型为Java属性和类型
                String attrName = CodeGenerationUtil.columnNameConvertToJavaAttrName(column.getColumnName());
                String attrType = CodeGenerationUtil.columnFieldConvertToJavaType(DB.MySQL, column.getColumnName(), column.getDataType());
                column.setAttrName(attrName);
                column.setAttrType(attrType);
            }

            // 获取模板
            Template entityTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.Entity);
            Template entityVOTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.EntityVO);
            Template addInputDTOTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.AddInputDTO);
            Template editInputDTOTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.EditInputDTO);
            Template queryPageInputDTOTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.QueryPageInputDTO);
            Template repositoryTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.Repository);
            Template mapperTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.Mapper);
            Template serviceTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.Service);
            Template serviceImplTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.ServiceImpl);
            Template controllerTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.Controller);
            Template apiTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.Api);
            Template indexTemplate = CodeGenerationUtil.getTemplate(CodeTemplateEnum.Index);


            StringWriter writer = new StringWriter();

            // 表名
            String tabName = inputDTO.getTableName();
            // 表注释
            String tabComments = inputDTO.getTableComment();
            // 模块名
            String moduleName = inputDTO.getModuleName();
            // 源码作者
            String author = "zuuuYao (https://github.com/HuangZhongYao)";
            // 源码生成时间
            String dateTime = DateUtil.now();
            // java代码包名
            String basePackage = inputDTO.getPackageName();
            // java代码生成路径
            String javaCodePath = "java" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
            // mapper.xml代码生成路径
            String mapperXMLPath = "java" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper" + File.separator;
            // 前端文件生成路径
            String vueCodePath = "vue" + File.separator + "src" + File.separator + "views" + File.separator + moduleName + File.separator;
            // 生成代码是否引BigDecimal类
            boolean hasBigDecimal = true;
            // 生成代码是否引LocalDateTime类
            boolean hasLocalDateTime = true;
            // 生成代码是否引LocalDate类
            boolean hasLocalDate = true;
            // 生成代码是否引LocalTime类
            boolean hasLocalTime = true;
            // 生成代码是否引Date类
            boolean hasDate = true;

            // service文件包名
            String servicePackageName = basePackage + ".service." + moduleName;
            // serviceImpl文件包名
            String serviceImplPackageName = basePackage + ".service." + moduleName + ".impl";
            // inputDTO文件包名
            String inputDTOPackageName = servicePackageName + ".dto.input";
            // output文件包名
            String outputDTOPackageName = servicePackageName + ".dto.output";

            // 定义输出
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(outputStream);

            // 实体文件名
            String entityClassName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Entity";
            // 实体类没有后缀的名称
            String domainName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName));
            // 实体类所在包名
            String entityPackageName = basePackage + ".entity" + "." + moduleName;
            // 实体类全路径
            String entityFullName = entityPackageName + "." + entityClassName;
            // 实体类注释
            String entityComments = tabComments + "实体";
            // 生成实体传入模板变量
            VelocityContext entityContext = new VelocityContext();
            entityContext.put("packageName", entityPackageName);
            entityContext.put("comments", entityComments);
            entityContext.put("author", author);
            entityContext.put("datetime", dateTime);
            entityContext.put("className", entityClassName);
            entityContext.put("tableName", tabName);
            entityContext.put("columns", columns);
            entityContext.put("hasBigDecimal", hasBigDecimal);
            entityContext.put("hasLocalDateTime", hasLocalDateTime);
            entityContext.put("hasLocalDate", hasLocalDate);
            entityContext.put("hasLocalTime", hasLocalTime);
            entityContext.put("hasDate", hasDate);

            writer.getBuffer().setLength(0);
            entityTemplate.merge(entityContext, writer);
            System.out.println();
            log.info("Entity==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(entityTemplate.getName(), javaCodePath + entityFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            zip.closeEntry();

            // 实体vo名
            String entityVOName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "VO";
            // 实体vo注释
            String entityVOComments = tabComments + "VO对象";
            // 实体vo全路径
            String entityVOFullName = outputDTOPackageName + "." + entityVOName;
            // 生成实体VO传入变量
            VelocityContext entityVOContext = new VelocityContext();
            entityVOContext.put("packageName", outputDTOPackageName);
            entityVOContext.put("comments", entityVOComments);
            entityVOContext.put("author", author);
            entityVOContext.put("datetime", dateTime);
            entityVOContext.put("className", entityVOName);
            entityVOContext.put("tableName", tabName);
            entityVOContext.put("columns", columns);
            entityVOContext.put("hasBigDecimal", hasBigDecimal);
            entityVOContext.put("hasLocalDateTime", hasLocalDateTime);
            entityVOContext.put("hasLocalDate", hasLocalDate);
            entityVOContext.put("hasLocalTime", hasLocalTime);
            entityVOContext.put("hasDate", hasDate);

            writer.getBuffer().setLength(0);
            entityVOTemplate.merge(entityVOContext, writer);
            System.out.println();
            log.info(
                    "EntityVO==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(entityVOTemplate.getName(), javaCodePath + entityVOFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            zip.closeEntry();

            // 添加数据DTO名
            String addInputDTOName = "Add" + StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "InputDTO";
            // 添加数据DTO类注释
            String addInputDTOComments = tabComments + "新增数据DTO对象";
            // 添加数据DTO全路径
            String addInputDTOFullName = inputDTOPackageName + "." + addInputDTOName;

            // 生成添加数据DTO类传入变量
            VelocityContext addInputDTOContext = new VelocityContext();
            addInputDTOContext.put("packageName", inputDTOPackageName);
            addInputDTOContext.put("comments", addInputDTOComments);
            addInputDTOContext.put("author", author);
            addInputDTOContext.put("datetime", dateTime);
            addInputDTOContext.put("className", addInputDTOName);
            addInputDTOContext.put("tableName", tabName);
            addInputDTOContext.put("columns", columns);
            addInputDTOContext.put("hasBigDecimal", hasBigDecimal);
            addInputDTOContext.put("hasLocalDateTime", hasLocalDateTime);
            addInputDTOContext.put("hasLocalDate", hasLocalDate);
            addInputDTOContext.put("hasLocalTime", hasLocalTime);
            addInputDTOContext.put("hasDate", hasDate);

            writer.getBuffer().setLength(0);
            addInputDTOTemplate.merge(addInputDTOContext, writer);
            System.out.println();
            log.info(
                    "AddInputDTO==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(addInputDTOTemplate.getName(), javaCodePath + addInputDTOFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            IOUtils.closeQuietly(writer);
            zip.closeEntry();


            // 编辑数据DTO类名
            String editInputDTOName = "Edit" + StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "InputDTO";
            String editInputDTOComments = tabComments + "编辑数据DTO对象";
            String editInputDTOFullName = inputDTOPackageName + "." + editInputDTOName;

            // 生成编辑数据DTO传入变量
            VelocityContext editInputDTOContext = new VelocityContext();
            editInputDTOContext.put("packageName", inputDTOPackageName);
            editInputDTOContext.put("comments", editInputDTOComments);
            editInputDTOContext.put("author", author);
            editInputDTOContext.put("datetime", dateTime);
            editInputDTOContext.put("className", editInputDTOName);
            editInputDTOContext.put("tableName", tabName);
            editInputDTOContext.put("columns", columns);
            editInputDTOContext.put("hasBigDecimal", hasBigDecimal);
            editInputDTOContext.put("hasLocalDateTime", hasLocalDateTime);
            editInputDTOContext.put("hasLocalDate", hasLocalDate);
            editInputDTOContext.put("hasLocalTime", hasLocalTime);
            editInputDTOContext.put("hasDate", hasDate);

            writer.getBuffer().setLength(0);
            editInputDTOTemplate.merge(editInputDTOContext, writer);
            System.out.println();
            log.info(
                    "EditInputDTO==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(editInputDTOTemplate.getName(), javaCodePath + editInputDTOFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            IOUtils.closeQuietly(writer);
            zip.closeEntry();


            // 生成查询参数DTO类名
            String queryPageInputDTOName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "QueryPageInputDTO";
            String queryPageInputDTOComments = tabComments + "分页查询DTO对象";
            String queryPageInputDTOFullName = inputDTOPackageName + "." + queryPageInputDTOName;
            // 生成查询参数DTO传入参数
            VelocityContext queryPageInputDTOContext = new VelocityContext();
            queryPageInputDTOContext.put("packageName", inputDTOPackageName);
            queryPageInputDTOContext.put("comments", queryPageInputDTOComments);
            queryPageInputDTOContext.put("author", author);
            queryPageInputDTOContext.put("datetime", dateTime);
            queryPageInputDTOContext.put("className", queryPageInputDTOName);
            queryPageInputDTOContext.put("tableName", tabName);
            queryPageInputDTOContext.put("columns", columns);
            queryPageInputDTOContext.put("hasBigDecimal", hasBigDecimal);
            queryPageInputDTOContext.put("hasLocalDateTime", hasLocalDateTime);
            queryPageInputDTOContext.put("hasLocalDate", hasLocalDate);
            queryPageInputDTOContext.put("hasLocalTime", hasLocalTime);
            queryPageInputDTOContext.put("hasDate", hasDate);

            writer.getBuffer().setLength(0);
            queryPageInputDTOTemplate.merge(queryPageInputDTOContext, writer);
            System.out.println();
            log.info(
                    "QueryPageInputDTO==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(queryPageInputDTOTemplate.getName(), javaCodePath + queryPageInputDTOFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            IOUtils.closeQuietly(writer);
            zip.closeEntry();


            // Repository类名
            String repositoryName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Repository";
            String repositoryFullName = basePackage + ".repository." + StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Repository";
            String repositoryComments = tabComments + "仓储层";
            // 生成Repository类传入参数
            VelocityContext repositoryContext = new VelocityContext();
            repositoryContext.put("entityFullName", entityFullName);
            repositoryContext.put("entityName", entityClassName);
            repositoryContext.put("comments", repositoryComments);
            repositoryContext.put("author", author);
            repositoryContext.put("datetime", dateTime);
            repositoryContext.put("className", repositoryName);
            repositoryContext.put("tableName", tabName);
            repositoryContext.put("columns", columns);
            repositoryContext.put("hasBigDecimal", hasBigDecimal);
            repositoryContext.put("hasLocalDateTime", hasLocalDateTime);
            repositoryContext.put("hasLocalDate", hasLocalDate);
            repositoryContext.put("hasLocalTime", hasLocalTime);
            repositoryContext.put("hasDate", hasDate);

            writer.getBuffer().setLength(0);
            repositoryTemplate.merge(repositoryContext, writer);
            System.out.println();
            log.info(
                    "Repository==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(repositoryTemplate.getName(), javaCodePath + repositoryFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            IOUtils.closeQuietly(writer);
            zip.closeEntry();


            // 生成Mapper文件
            String mapperName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Mapper";
            VelocityContext mapperContext = new VelocityContext();
            mapperContext.put("irepository", repositoryName);

            writer.getBuffer().setLength(0);
            mapperTemplate.merge(mapperContext, writer);
            System.out.println();
            log.info("Mapper==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(mapperTemplate.getName(), mapperXMLPath + mapperName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            IOUtils.closeQuietly(writer);
            zip.closeEntry();

            // Service类名
            String serviceName = "I" + StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Service";
            String serviceFullName = servicePackageName + "." + serviceName;
            String serviceComments = tabComments + "Service服务接口层";
            // 生成Service类传入参数
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
            serviceContext.put("hasBigDecimal", hasBigDecimal);
            serviceContext.put("hasLocalDateTime", hasLocalDateTime);
            serviceContext.put("hasLocalDate", hasLocalDate);
            serviceContext.put("hasLocalTime", hasLocalTime);
            serviceContext.put("hasDate", hasDate);

            writer.getBuffer().setLength(0);
            serviceTemplate.merge(serviceContext, writer);
            System.out.println();
            log.info(
                    "Service==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(serviceTemplate.getName(), javaCodePath + serviceFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            IOUtils.closeQuietly(writer);
            zip.closeEntry();


            // ServiceImpl类名
            String serviceImplName =
                    "I" + StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "ServiceImpl";
            String serviceImplFullName = serviceImplPackageName + "." + serviceImplName;
            String serviceImplComments = tabComments + "Service服务接口实现层";

            // 生成ServiceImpl类传入参数
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
            serviceImplContext.put("hasBigDecimal", hasBigDecimal);
            serviceImplContext.put("hasLocalDateTime", hasLocalDateTime);
            serviceImplContext.put("hasLocalDate", hasLocalDate);
            serviceImplContext.put("hasLocalTime", hasLocalTime);
            serviceImplContext.put("hasDate", hasDate);

            writer.getBuffer().setLength(0);
            serviceImplTemplate.merge(serviceImplContext, writer);
            System.out.println();
            log.info(
                    "ServiceImpl==================================================\n\n" + writer);

            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(serviceImplTemplate.getName(), javaCodePath + serviceImplFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            IOUtils.closeQuietly(writer);
            zip.closeEntry();

            // Controller类名
            String controllerName = StrUtil.upperFirst(StrUtil.toCamelCase(tabName)) + "Controller";
            String controllerFullName = basePackage + ".web." + controllerName;
            String controllerComments = tabComments + "Controller层,接受处理请求";
            // 生成Controller类传入参数
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
            controllerContext.put("hasBigDecimal", hasBigDecimal);
            controllerContext.put("hasLocalDateTime", hasLocalDateTime);
            controllerContext.put("hasLocalDate", hasLocalDate);
            controllerContext.put("hasLocalTime", hasLocalTime);
            controllerContext.put("hasDate", hasDate);

            writer.getBuffer().setLength(0);
            controllerTemplate.merge(controllerContext, writer);
            System.out.println();
            log.info(
                    "Controller==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(controllerTemplate.getName(), javaCodePath + controllerFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            IOUtils.closeQuietly(writer);
            zip.closeEntry();


            // API JS
            String apiName = "api";
            String apiFullName = vueCodePath + File.separator + apiName;
            VelocityContext apiContext = new VelocityContext();
            apiContext.put("tab", tabComments.replaceAll("表", ""));
            apiContext.put("author", author);
            apiContext.put("datetime", dateTime);
            apiContext.put("requestMapping", moduleName);
            apiContext.put("domainName", domainName);

            writer.getBuffer().setLength(0);
            apiTemplate.merge(apiContext, writer);
            System.out.println();
            log.info(
                    "API JS==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(apiTemplate.getName(), apiFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            IOUtils.closeQuietly(writer);
            zip.closeEntry();

            // Index.vue
            String indexVueName = "index";
            String indexVueFullName = vueCodePath + File.separator + indexVueName;
            String indexVueComments = moduleName + "管理";

            // 权限编码
            String permissionCode = domainName + "Mgt";
            String addPermissionCode = "Add" + domainName;
            String editPermissionCode = "Edit" + domainName;
            String delPermissionCode = "Del" + domainName;

            VelocityContext indexVueContext = new VelocityContext();
            indexVueContext.put("comments", indexVueComments);
            indexVueContext.put("author", author);
            indexVueContext.put("datetime", dateTime);
            indexVueContext.put("tab", tabComments.replaceAll("表", ""));
            indexVueContext.put("columns", columns);
            indexVueContext.put("permissionCode", permissionCode);
            indexVueContext.put("AddPermissionCode", addPermissionCode);
            indexVueContext.put("EditPermissionCode", editPermissionCode);
            indexVueContext.put("DelPermissionCode", delPermissionCode);
            indexVueContext.put("usd", "$");

            writer.getBuffer().setLength(0);
            indexTemplate.merge(indexVueContext, writer);
            System.out.println();
            log.info(
                    "Index.vue ==================================================\n\n" + writer);
            //添加到zip
            zip.putNextEntry(new ZipEntry(
                    CodeGenerationUtil.getFileName(indexTemplate.getName(), indexVueFullName)
            ));
            IOUtils.write(writer.toString(), zip, CharsetUtil.UTF_8);
            IOUtils.closeQuietly(writer);
            zip.closeEntry();

            // 添加到资源管理
            if (inputDTO.getAddResources()) {
                String tabDesc = tabComments.replaceAll("表", "");
                // 判断权限编码是否重复
                if (resourcesRepository.selectCount(Wrappers.<ResourcesEntity>lambdaQuery().eq(ResourcesEntity::getCode, permissionCode)) > 0) {
                    throw new UserFriendlyException("该表已生成");
                }
                // 路由
                String routingPath = "/" + permissionCode;
                // 所属菜单
                ResourcesEntity parentMenu;
                if (inputDTO.getParentMenuId() != null) {
                    // 获取父级菜单
                    parentMenu = resourcesRepository.selectById(inputDTO.getParentMenuId());
                    if (null == parentMenu)
                        throw new UserFriendlyException("选择的所属菜单无效");
                    // 设置路由
                    routingPath = "/" + parentMenu.getCode() + routingPath;
                } else {
                    // 顶级菜单
                    parentMenu = null;
                }

                // 资源实体集合
                List<ResourcesEntity> resourcesEntities = new ArrayList<>();

                ResourcesEntity manageResources = ResourcesEntity
                        .builder()
                        .name(tabDesc + "管理")
                        .code(permissionCode)
                        .type(ResourcesTypeEnum.MENU)
                        .path(routingPath)
                        .component("/src/views/" + moduleName + "/index.vue")
                        .keepAlive(false)
                        .enable(true)
                        .isShow(true)
                        .description("创建" + tabDesc + "按钮")
                        .icon("i-fe:menu")
                        .sort("0")
                        .parentId(parentMenu == null ? null : parentMenu.getId())
                        .build();

                this.resourcesRepository.insert(manageResources);

                resourcesEntities.add(
                        ResourcesEntity
                                .builder()
                                .name("创建按钮")
                                .code(addPermissionCode)
                                .type(ResourcesTypeEnum.BUTTON)
                                .keepAlive(false)
                                .enable(true)
                                .isShow(true)
                                .description("创建" + tabDesc + "按钮")
                                .icon("i-me:btn")
                                .sort("0")
                                .parentId(manageResources.getId())
                                .build()
                );
                resourcesEntities.add(
                        ResourcesEntity
                                .builder()
                                .name("编辑按钮")
                                .code(editPermissionCode)
                                .type(ResourcesTypeEnum.BUTTON)
                                .keepAlive(false)
                                .enable(true)
                                .isShow(true)
                                .description("编辑" + tabDesc + "按钮")
                                .icon("i-me:btn")
                                .sort("0")
                                .parentId(manageResources.getId())
                                .build()
                );
                resourcesEntities.add(ResourcesEntity
                        .builder()
                        .name("删除按钮")
                        .code(delPermissionCode)
                        .type(ResourcesTypeEnum.BUTTON)
                        .keepAlive(false)
                        .enable(true)
                        .isShow(true)
                        .description("删除" + tabDesc + "按钮")
                        .icon("i-me:btn")
                        .sort("0")
                        .parentId(manageResources.getId())
                        .build());

                resourcesRepository.insert(resourcesEntities, resourcesEntities.size());
            }

            // 关闭流 一定要在return之前
            IOUtils.closeQuietly(zip);
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(writer);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + moduleName + ".zip");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            // 构建响应体
            ResponseEntity<byte[]> body = ResponseEntity.<byte[]>ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(outputStream.toByteArray());

            // 返回响应体
            return body;
        } catch (Exception e) {
            log.error("渲染 {} 表出错, 错误: {}", inputDTO.getTableName(), e.getMessage());
            // 抛出异常触发回滚
            throw new UserFriendlyException("渲染" + inputDTO.getTableName() + "表出错, 错误: " + e.getMessage());
        }
    }
}
