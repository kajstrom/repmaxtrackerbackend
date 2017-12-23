package fi.kajstrom.repmaxtracker.infrastructure.database;


import fi.kajstrom.repmaxtracker.domain.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("exerciseGateway")
public class ExerciseGateway {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ExerciseGateway(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Exercise> getAll() {
        return jdbcTemplate.query("SELECT * FROM exercises", (rs, rowNum) -> {
            return new Exercise(
                    rs.getLong("exercise_id"),
                    rs.getString("name")
            );
        });
    }
}
