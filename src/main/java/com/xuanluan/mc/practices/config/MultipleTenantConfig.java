package com.xuanluan.mc.practices.config;

import com.xuanluan.mc.sdk.service.ITenantService;
import com.xuanluan.mc.sdk.service.imp.PostgresTenantServiceImp;
import com.xuanluan.mc.sdk.service.tenant.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

@Configuration
public class MultipleTenantConfig {
    @Value("${flyway.schema_location}")
    private String schemaLocation;
    @Value("${flyway.schema.excludes}")
    private String[] excludeNames;

    @Bean
    ITenantIdentifierResolver tenantIdentifierResolver() {
        return new TenantIdentifierResolver();
    }

    @Bean
    ITenantConnectionProvider tenantConnectionProvider(DataSource dataSource) {
        return new TenantConnectionProvider(dataSource);
    }

    @Bean
    ITenantProvider tenantProvider(DataSource dataSource) {
        return new TenantProvider(dataSource, new String[]{schemaLocation});
    }

    @Bean
    ITenantService tenantService(EntityManager entityManager) {
        return new PostgresTenantServiceImp(entityManager, List.of(excludeNames));
    }

}
