package com.eazybytes.accounts.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix="accounts")
@Getter
@Setter
@ToString
public class AccountServiceConfig implements Serializable {
    private String msg;
    private String buildVersion;
    private Map<String,String> mailDetails;
    private List<String> activeBranches;
}