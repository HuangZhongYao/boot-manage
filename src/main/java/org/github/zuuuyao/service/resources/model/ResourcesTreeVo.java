package org.github.zuuuyao.service.resources.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.output.BaseOutputIdAndTimeDTO;
import org.github.zuuuyao.common.util.tree.ITreeNode;
import org.github.zuuuyao.entity.enums.ResourcesTypeEnum;

import java.io.Serial;
import java.util.List;

/**
 * @Desc
 * @Time 2024-07-15 15:08
 * @Author HuangZhongYao
 */
@Getter
@Setter
@ToString
public class ResourcesTreeVo extends BaseOutputIdAndTimeDTO implements ITreeNode<Long> {


    @Serial
    private static final long serialVersionUID = -587179557810410708L;

    /**
     * 编码
     */
    @Schema(description = "编码")
    private String code;

    /**
     * 资源名称
     */
    @Schema(description = "资源名称")
    private String name;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String component;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 是否显示
     */
    @Schema(description = "是否显示")
    private Boolean isShow;


    /**
     * 是否显示
     */
    @Schema(description = "是否显示")
    private Boolean show;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enable;

    /**
     * keepAlive 是否组件之间切换时缓存它们的状态
     */
    @Schema(description = "keepAlive 是否组件之间切换时缓存它们的状态")
    private Boolean keepAlive;

    /**
     * 资源类型
     */
    @Schema(description = "资源类型")
    private ResourcesTypeEnum type;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private String sort;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private String order;

    /**
     * 上级资源id
     */
    @Schema(description = "上级资源id")
    private Long parentId;

    /**
     * 下级资源集合
     */
    @Schema(description = "下级资源集合")
    List<ITreeNode<Long>> children;

    public Boolean getShow() {
        return isShow;
    }

    public String getOrder() {
        return sort;
    }

}
