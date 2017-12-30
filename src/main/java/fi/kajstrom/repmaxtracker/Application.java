package fi.kajstrom.repmaxtracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
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

        jdbcTemplate.execute("DROP TABLE exercises IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE exercises(" +
            "exercise_id IDENTITY, name VARCHAR(100))");

        jdbcTemplate.execute("INSERT INTO exercises(name) VALUES('Deadlift')");
        jdbcTemplate.execute("INSERT INTO exercises(name) VALUES('Back Squat')");
        jdbcTemplate.execute("INSERT INTO exercises(name) VALUES('Bench Press')");

        jdbcTemplate.execute("DROP TABLE users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE users (" +
            "user_id IDENTITY," +
            "firstname VARCHAR(200)," +
            "lastname VARCHAR(200)," +
            ")");

        jdbcTemplate.execute("INSERT INTO users(firstname, lastname) VALUES('Kaj', 'Ström')");
        jdbcTemplate.execute("INSERT INTO users(firstname, lastname) VALUES('Ville', 'Viklund')");

        jdbcTemplate.execute("DROP TABLE sets IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE sets(" +
            "set_id IDENTITY," +
            "user_id INT," +
            "exercise_id INT NOT NULL," +
            "performed_on DATE NOT NULL, " +
            "weight DOUBLE NOT NULL," +
            "repetitions TINYINT NOT NULL, " +
            "estimated_1rm DOUBLE NOT NULL," +
            "FOREIGN KEY(user_id) REFERENCES users(user_id)," +
            "FOREIGN KEY(exercise_id) REFERENCES exercises(exercise_id))");
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