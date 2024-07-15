package org.github.zuuuyao.common.base.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @Desc 基础输出对象带id和时间字段
 * @Time 2024-07-12 10:41
 * @Author HuangZhongYao
 */
public class BaseOutputIdAndTimeDTO extends BaseOutputDTO {

    @Serial
    private static final long serialVersionUID = -8243192684166209735L;


    public BaseOutputIdAndTimeDTO() {
    }

    public BaseOutputIdAndTimeDTO(LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public BaseOutputIdAndTimeDTO(Long id, LocalDateTime createdTime, LocalDateTime updatedTime) {
        super(id);
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    /**
     * 创建时间
     */
    @Schema(description = "创建时间",name = "createdTime", example = "2024-07-12 10:11:55")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", name = "updatedTime", example = "2024-07-12 10:11:55")
    private LocalDateTime updatedTime;

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}
