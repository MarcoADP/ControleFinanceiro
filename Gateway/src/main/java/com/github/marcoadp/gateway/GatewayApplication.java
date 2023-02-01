package com.github.marcoadp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/bancario/**").uri("lb://msbancario"))
                /* TODO ADICIONAR ROTAS
                .route(r -> r.path("/acesso/**").uri("lb://msacesso"))
                .route(r -> r.path("/financeiro/**").uri("lb://msfinanceiro"))
                .route(r -> r.path("/investimento/**").uri("lb://msinvestimento"))
                 */
                .build();
    }

}
