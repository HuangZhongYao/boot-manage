package org.github.zuuuyao.common.util;

import cn.hutool.core.util.StrUtil;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.exception.UserFriendlyException;

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

    // mysql字段类型映射
    private static final Map<String, String> mysqlTypeMap = new HashMap<>();
    // sqlServer字段类型映射
    private static final Map<String, String> sqlServerTypeMap = new HashMap<>();
    // postgreSQL字段类型映射
    private static final Map<String, String> postgreSQLTypeMap = new HashMap<>();
    // oracle字段类型映射
    private static final Map<String, String> oracleTypeMap = new HashMap<>();

    static {
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

}
