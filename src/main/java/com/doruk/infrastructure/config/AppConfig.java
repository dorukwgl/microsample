package com.doruk.infrastructure.config;


import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("micronaut.application")
public record AppConfig(String name,
                        String publicUploadPath,
                        String privateUploadPath,
                        String appId,
                        int sessionExpiration,
                        String appUrl,
                        boolean cookieSecure,
                        String tempDir,
                        long profileIconMaxSize) {
}
