package org.github.zuuuyao.common.util;

import ua_parser.*;

/**
 * @Desc
 * @Time 2024-07-23 16:09
 * @Author HuangZhongYao
 */
public final class RequestUtil {

    /**
     * 解析请求中的user-Agent
     */
    public final static Parser USER_AGENT_PARSER = new Parser();


    public static UserAgentInfo parseUserAgent(String userAgentStr) {
        Client client = USER_AGENT_PARSER.parse(userAgentStr);

        if (null == client) {
            return UserAgentInfo.builder().build();
        }

        UserAgent userAgent = client.userAgent;
        OS os = client.os;
        Device device = client.device;

        return UserAgentInfo
                .builder()
                .browser(userAgent.family)
                .browserVersion(userAgent.major + "." + client.userAgent.minor)
                .os(client.os.family)
                .osVersion(os.major)
                .device(device.family)
                .build();
    }

}

