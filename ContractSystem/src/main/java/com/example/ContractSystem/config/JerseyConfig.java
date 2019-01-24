package com.example.ContractSystem.config;

import com.example.ContractSystem.controller.ContractController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ContractController.class);
    }
}
