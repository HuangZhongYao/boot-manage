package org.github.zuuuyao.service.user.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.zuuuyao.common.base.dto.input.BaseLongIdInputDTO;

/**
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-25 22:50
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SetUserStateInputDTO extends BaseLongIdInputDTO {

    @Serial
    private static final long serialVersionUID = -7374695140327319136L;

    @Schema(description = "状态")
    private Boolean state;
}
