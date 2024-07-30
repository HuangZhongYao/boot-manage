package org.github.zuuuyao.common.util.generate;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import lombok.*;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.util.DB;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * 代码生成器工具类
 *
 * @Desc Created by IntelliJ IDEA.
 * @Author ZhongYao.Huang (https://github.com/HuangZhongYao)
 * @Copyright ZuuuuYao By Github
 * @Time 2024-07-28 22:23
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CodeGenerationUtil {
    private CodeGenerationUtil() {
    }

    // 创建 Velocity 引擎
    public static final VelocityEngine velocityEngine;
    // 模板map
    private static final Map<CodeTemplateEnum, Template> templateMap;


    // mysql字段类型映射
    private static final Map<String, String> mysqlTypeMap = new HashMap<>();
    // sqlServer字段类型映射
    private static final Map<String, String> sqlServerTypeMap = new HashMap<>();
    // postgreSQL字段类型映射
    private static final Map<String, String> postgreSQLTypeMap = new HashMap<>();
    // oracle字段类型映射
    private static final Map<String, String> oracleTypeMap = new HashMap<>();

    static {

        // 初始化模板引擎
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        // 初始化模板
        templateMap = new HashMap<>();
        templateMap.put(CodeTemplateEnum.Entity, velocityEngine.getTemplate("templates/Entity.java.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.EntityVO, velocityEngine.getTemplate("templates/EntityVO.java.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.AddInputDTO, velocityEngine.getTemplate("templates/AddInputDTO.java.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.EditInputDTO, velocityEngine.getTemplate("templates/EditInputDTO.java.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.QueryPageInputDTO, velocityEngine.getTemplate("templates/QueryPageInputDTO.java.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.Repository, velocityEngine.getTemplate("templates/Repository.java.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.Mapper, velocityEngine.getTemplate("templates/Mapper.xml.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.Service, velocityEngine.getTemplate("templates/Service.java.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.ServiceImpl, velocityEngine.getTemplate("templates/ServiceImpl.java.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.Controller, velocityEngine.getTemplate("templates/Controller.java.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.Api, velocityEngine.getTemplate("templates/api.js.vm", CharsetUtil.UTF_8));
        templateMap.put(CodeTemplateEnum.Index, velocityEngine.getTemplate("templates/Index.vue.vm", CharsetUtil.UTF_8));

        // MySQL types
        mysqlTypeMap.put("CHAR", "String");
        mysqlTypeMap.put("VARCHAR", "String");
        mysqlTypeMap.put("LONGTEXT", "String");
        mysqlTypeMap.put("TEXT", "String");
        mysqlTypeMap.put("NUMERIC", "BigDecimal");
        mysqlTypeMap.put("DECIMAL", "BigDecimal");
        mysqlTypeMap.put("BIT", "Boolean");
        mysqlTypeMap.put("TINYINT", "Byte");
        mysqlTypeMap.put("SMALLINT", "Short");
        mysqlTypeMap.put("INTEGER", "Integer");
        mysqlTypeMap.put("INT", "Integer");
        mysqlTypeMap.put("BIGINT", "Long");
        mysqlTypeMap.put("REAL", "Float");
        mysqlTypeMap.put("FLOAT", "Double");
        mysqlTypeMap.put("DOUBLE", "Double");
        mysqlTypeMap.put("BINARY", "byte[]");
        mysqlTypeMap.put("VARBINARY", "byte[]");
        mysqlTypeMap.put("DATE", "LocalDate");
        mysqlTypeMap.put("DATETIME", "LocalDateTime");
        mysqlTypeMap.put("TIME", "Time");
        mysqlTypeMap.put("TIMESTAMP", "Timestamp");

        // SQL Server types
        sqlServerTypeMap.put("CHAR", "String");
        sqlServerTypeMap.put("VARCHAR", "String");
        sqlServerTypeMap.put("LONGVARCHAR", "String");
        sqlServerTypeMap.put("TEXT", "String");
        sqlServerTypeMap.put("NUMERIC", "BigDecimal");
        sqlServerTypeMap.put("DECIMAL", "BigDecimal");
        sqlServerTypeMap.put("BIT", "Boolean");
        sqlServerTypeMap.put("TINYINT", "Byte");
        sqlServerTypeMap.put("SMALLINT", "Short");
        sqlServerTypeMap.put("INTEGER", "Integer");
        sqlServerTypeMap.put("BIGINT", "Long");
        sqlServerTypeMap.put("REAL", "Float");
        sqlServerTypeMap.put("FLOAT", "Double");
        sqlServerTypeMap.put("DOUBLE", "Double");
        sqlServerTypeMap.put("BINARY", "byte[]");
        sqlServerTypeMap.put("VARBINARY", "byte[]");
        sqlServerTypeMap.put("LONGVARBINARY", "byte[]");
        sqlServerTypeMap.put("DATE", "Date");
        sqlServerTypeMap.put("TIME", "Time");
        sqlServerTypeMap.put("TIMESTAMP", "Timestamp");
        sqlServerTypeMap.put("DATETIME", "Timestamp");
        sqlServerTypeMap.put("SMALLDATETIME", "Timestamp");

        // PostgreSQL types
        postgreSQLTypeMap.put("CHAR", "String");
        postgreSQLTypeMap.put("VARCHAR", "String");
        postgreSQLTypeMap.put("TEXT", "String");
        postgreSQLTypeMap.put("NUMERIC", "BigDecimal");
        postgreSQLTypeMap.put("DECIMAL", "BigDecimal");
        postgreSQLTypeMap.put("BOOLEAN", "Boolean");
        postgreSQLTypeMap.put("SMALLINT", "Short");
        postgreSQLTypeMap.put("INTEGER", "Integer");
        postgreSQLTypeMap.put("BIGINT", "Long");
        postgreSQLTypeMap.put("REAL", "Float");
        postgreSQLTypeMap.put("DOUBLE PRECISION", "Double");
        postgreSQLTypeMap.put("BYTEA", "byte[]");
        postgreSQLTypeMap.put("DATE", "Date");
        postgreSQLTypeMap.put("TIME", "Time");
        postgreSQLTypeMap.put("TIMESTAMP", "Timestamp");

        // Oracle types
        oracleTypeMap.put("CHAR", "String");
        oracleTypeMap.put("VARCHAR2", "String");
        oracleTypeMap.put("LONG", "String");
        oracleTypeMap.put("NUMBER", "BigDecimal");
        oracleTypeMap.put("FLOAT", "Float");
        oracleTypeMap.put("BINARY_FLOAT", "Float");
        oracleTypeMap.put("BINARY_DOUBLE", "Double");
        oracleTypeMap.put("DATE", "Date");
        oracleTypeMap.put("TIMESTAMP", "Timestamp");
        oracleTypeMap.put("RAW", "byte[]");
        oracleTypeMap.put("LONG RAW", "byte[]");
        oracleTypeMap.put("BLOB", "byte[]");
        oracleTypeMap.put("CLOB", "String");
        oracleTypeMap.put("NCLOB", "String");
    }

    /**
     * 数据库字段类型转换为Java类型
     *
     * @param dbType     数据库平台
     * @param columnType 数据库字段类型
     * @return 对应的Java类
     */
    public static String columnFieldConvertToJavaType(DB dbType, String columnName,
                                                      String columnType) {
        // java17 switch新语法
        Map<String, String> typeMap =
                switch (dbType) {
                    case MySQL -> mysqlTypeMap;
                    case SQLServer -> sqlServerTypeMap;
                    case PostgreSQL -> postgreSQLTypeMap;
                    case Oracle -> oracleTypeMap;
                };

        String javaType = typeMap.get(columnType.toUpperCase());

        if (null == javaType) {
            throw new UserFriendlyException("字段: " + columnName + ",不支持的类型" + columnType);
        }

        return javaType;
    }

    /**
     * 将下划线方式命名的字符串转换为首字母小写驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。
     * 例如：hello_world =》helloWorld
     *
     * @param columnName 列名称
     * @return 首字母小写驼峰式
     */
    public static String columnNameConvertToJavaAttrName(String columnName) {
        return StrUtil.lowerFirst(StrUtil.toCamelCase(columnName));
    }

    /**
     * 获取代码模板
     * @param templateEnum 模板枚举
     * @return 模板
     */
    public static Template getTemplate(CodeTemplateEnum templateEnum){
        return templateMap.get(templateEnum);
    }

    /**
     * 获取文件名
     *
     * @param templateName 模板名
     * @param path         路径
     * @return 文件名
     */
    public static String getFileName(String templateName, String path) {

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

        return filePath;
    }

}
