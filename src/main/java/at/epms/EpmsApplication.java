package at.epms;

import java.util.HashMap;

import at.epms.config.EpmsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "at.epms")
@EnableConfigurationProperties({ EpmsProperties.class })
public class EpmsApplication {

    @SuppressWarnings("resource")
    public static void main(String... args) {

        final var app = new SpringApplication(EpmsApplication.class);
        setDevelopmentProfileIfNoOthersGiven(app);
        app.run(args);

    }

    private static void setDevelopmentProfileIfNoOthersGiven(final SpringApplication app) {

        final var properties = new HashMap<String, Object>();
        properties.put("spring.profiles.default", "dev");
        app.setDefaultProperties(properties);

    }

}
