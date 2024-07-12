package org.github.zuuuyao.common.response;

import java.io.Serial;

/**
 * @Desc 错误响应对象
 * @Time 2024-07-12 16:09
 * @Author HuangZhongYao
 */
public class ErrorResponse extends AbstractResponse{

    @Serial
    private static final long serialVersionUID = -8369388190571799068L;

    @Override
    public String getMsg() {
        return super.getMsg() != null ? super.getMsg() : "操作失败";
    }

    @Override
    public Boolean getSuccess() {
        return false;
    }
}
