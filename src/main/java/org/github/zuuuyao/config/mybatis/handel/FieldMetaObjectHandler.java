package org.github.zuuuyao.config.mybatis.handel;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis-plus字段自动填充处理器
 *
 * @Desc
 * @Time 2024-07-12 09:41
 * @Author HuangZhongYao
 */
@Slf4j
@Component
public class FieldMetaObjectHandler implements MetaObjectHandler {

    /**
     * 填充插入填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        // 填充插入时间 ，注意字段名要对不然是无效的
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, now);
        // 首次插入时填充更新时间字段值
        this.strictInsertFill(metaObject, "updatedTime", LocalDateTime.class, now);
    }

    /**
     * 填充更新时填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        // 填充更新时间
        this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime.class, now);
    }
}
