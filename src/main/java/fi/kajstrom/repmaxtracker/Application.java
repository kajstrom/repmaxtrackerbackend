package fi.kajstrom.repmaxtracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void run(String... args) throws Exception {
        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE greetings IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE greetings(" +
            "id IDENTITY, greeting VARCHAR(255), created_at TIMESTAMP)");

        jdbcTemplate.execute("DROP TABLE exercises IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE exercises(" +
            "exercise_id IDENTITY, name VARCHAR(100))");

        jdbcTemplate.execute("INSERT INTO exercises(name) VALUES('Deadlift')");
        jdbcTemplate.execute("INSERT INTO exercises(name) VALUES('Back Squat')");
        jdbcTemplate.execute("INSERT INTO exercises(name) VALUES('Bench Press')");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .allowedHeaders("*");
            }
        };
    }
}