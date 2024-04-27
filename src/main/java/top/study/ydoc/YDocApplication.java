package top.study.ydoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The type Y-Doc application.
 *
 * @author tjy
 * @date 2024 /04/13
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class YDocApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(YDocApplication.class, args);
    }

}
