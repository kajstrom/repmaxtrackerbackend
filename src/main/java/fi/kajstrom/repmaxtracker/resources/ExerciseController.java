package fi.kajstrom.repmaxtracker.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<ExerciseResource> all() {
        return jdbcTemplate.query("SELECT * FROM exercises", (rs, rowNum) -> {
           return new ExerciseResource(
                   rs.getLong("exercise_id"),
                   rs.getString("name")
           );
        });
    }
}
