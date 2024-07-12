package org.github.zuuuyao.common.base.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @Desc 基础输出对象带id、时间操作人字段
 * @Time 2024-07-12 10:41
 * @Author HuangZhongYao
 */
public class BaseOutputIdAndTimeAndOperationDTO extends BaseOutputIdAndTimeDTO {

    @Serial
    private static final long serialVersionUID = -8243192684166209735L;


    public BaseOutputIdAndTimeAndOperationDTO() {
    }

    public BaseOutputIdAndTimeAndOperationDTO(String createdBy, String updatedBy) {
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public BaseOutputIdAndTimeAndOperationDTO(LocalDateTime createdTime, LocalDateTime updatedTime, String createdBy, String updatedBy) {
        super(createdTime, updatedTime);
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public BaseOutputIdAndTimeAndOperationDTO(String id, LocalDateTime createdTime, LocalDateTime updatedTime, String createdBy, String updatedBy) {
        super(id, createdTime, updatedTime);
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    /**
     * 创建时间
     */
    @Schema(name = "创建人", example = "144344665")
    private String createdBy;

    /**
     * 更新时间
     */
    @Schema(name = "更新人", example = "153344665")
    private String updatedBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
