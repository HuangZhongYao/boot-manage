package org.github.zuuuyao.common.util;

import lombok.*;

/**
 * @Desc
 * @Time 2024-07-23 16:20
 * @Author HuangZhongYao
 */
/**
 * 解析信息
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAgentInfo {
    /**
     * 操作系统
     */
    private String os;
    /**
     * 操作系统版本
     */
    private String osVersion;
    /**
     * 设备类型
     */
    private String device;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 浏览器版本
     */
    private String browserVersion;
}
