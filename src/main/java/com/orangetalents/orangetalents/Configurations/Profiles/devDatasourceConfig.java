package com.orangetalents.orangetalents.Configurations.Profiles;

import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Profile("dev")
public class devDatasourceConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server(){
        Server h2Server;
        try {
            h2Server = Server.createTcpServer();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }
        return h2Server;
    }
}
