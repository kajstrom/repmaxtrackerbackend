package fi.kajstrom.repmaxtracker.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Exercise> all() {
        return jdbcTemplate.query("SELECT * FROM exercises", (rs, rowNum) -> {
           return new Exercise(
                   rs.getLong("exercise_id"),
                   rs.getString("name")
           );
        });
    }
}
