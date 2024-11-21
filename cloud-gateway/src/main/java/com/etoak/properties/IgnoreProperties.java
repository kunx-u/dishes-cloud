package com.etoak.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 白名单
 */
@Data
@Component
@ConfigurationProperties(prefix = "ignore")
public class IgnoreProperties {

    private List<String> uriList;


}
