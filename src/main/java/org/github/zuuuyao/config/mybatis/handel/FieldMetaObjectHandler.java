package org.github.zuuuyao.config.mybatis.handel;

import cn.dev33.satoken.stp.StpUtil;
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
        // 填充创建人
        String operator = this.getLoginUserId();
        this.strictInsertFill(metaObject, "createdBy", String.class, operator);
        this.strictInsertFill(metaObject, "updatedBy", String.class, operator);
        // 填充插入时间 ，注意字段名要对不然是无效的
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updatedTime", LocalDateTime.class, now);
    }

    /**
     * 填充更新时填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 填充更新人
        String operator = this.getLoginUserId();
        this.strictInsertFill(metaObject, "updatedBy", String.class, operator);
        // 填充更新时间
        LocalDateTime now = LocalDateTime.now();
        this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime.class, now);
    }

    /**
     * 获取当前登录人id
     * @return
     */
    private String getLoginUserId(){
        // 获取操作人
        Object loginId = StpUtil.getLoginIdDefaultNull();
        if (null == loginId) {
            return null;
        }
        return String.valueOf(loginId);
    }
}
