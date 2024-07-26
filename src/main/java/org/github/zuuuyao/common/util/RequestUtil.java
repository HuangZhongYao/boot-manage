package org.github.zuuuyao.common.util;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;

/**
 * @Desc
 * @Time 2024-07-23 16:09
 * @Author HuangZhongYao
 */
public final class RequestUtil {


    public static UserAgentInfo parseUserAgent(String userAgentStr) {

        UserAgent client = UserAgentUtil.parse(userAgentStr);

        if (null == client) {
            return UserAgentInfo.builder().build();
        }

        return UserAgentInfo
                .builder()
                .browser(client.getBrowser().getName())
                .browserVersion(client.getVersion())
                .os(client.getOs().toString())
                .osVersion(client.getOsVersion())
                .device(client.getOsVersion())
                .platform(client.getPlatform().getName())
                .build();
    }

}

