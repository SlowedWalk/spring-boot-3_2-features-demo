package tech.hidetora.springboot3_2demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestApplication {

    @Bean
    @ServiceConnection
    @RestartScope
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:15"));
    }

//    @Bean
//    @ServiceConnection(name = "openzipkin/zipkin")
//    GenericContainer<?> zipkinContainer() {
//        return new GenericContainer<>(DockerImageName.parse("openzipkin/zipkin:latest")).withExposedPorts(9411);
//    }

    public static void main(String[] args) {
        SpringApplication.from(Application::main).with(TestApplication.class).run(args);
    }

}
