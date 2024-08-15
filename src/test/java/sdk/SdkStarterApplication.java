package sdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.xuanluan.mc.sdk", "com.xuanluan.mc.auth"})
@EntityScan(basePackages = {"com.xuanluan.mc.sdk", "com.xuanluan.mc.auth"})
@ComponentScan(basePackages = {"com.xuanluan.mc.sdk", "com.xuanluan.mc.auth"})
@SpringBootApplication
public class SdkStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SdkStarterApplication.class, args);
    }
}
