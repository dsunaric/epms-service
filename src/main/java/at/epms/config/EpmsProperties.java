package at.epms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "epms", ignoreUnknownFields = false)
public class EpmsProperties {
}
