package com.boot.template.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yishuai
 * @date 2022-09-16 15:19
 **/
@Component
@ConfigurationProperties(prefix = "tools")
@Data
public class BootTamplateConfig {
    private String deployModel;

    private String ticketKey;

    private String casServerUrl;

    private String casLogin;

    private String casServerName;

    private String authSwitch;

    private String authExpireTime;

    public Boolean isOnline() {
        return "1".equals(deployModel);
    }

    public long getAuthTimestamp() {
        return Long.parseLong(authExpireTime);
    }
}
