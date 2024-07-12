package org.github.zuuuyao.common.response.annotations;

import java.lang.annotation.*;

/**
 * 接口忽略包装,标注在controller上整个controller都不进行包装，标记在方法上该方法不包装api响应结果
 * @Desc 标记该接口的返回值不进行包装
 * @Time 2024-07-12 16:18
 * @Author HuangZhongYao
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiIgnoreWrapper {
}
