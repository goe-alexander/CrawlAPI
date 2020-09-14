package crawlapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
/*@EnableJpaRepositories
@Slf4j
@EnableAsync
@EntityScan
@ComponentScan({"crawlapi"})*/
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}