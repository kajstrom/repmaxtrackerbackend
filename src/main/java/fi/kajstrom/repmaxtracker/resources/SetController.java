package fi.kajstrom.repmaxtracker.resources;


import fi.kajstrom.repmaxtracker.domain.SetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlRowSetResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

@RestController
@RequestMapping("/sets")
public class SetController {
    private static final Logger log = LoggerFactory.getLogger(SetController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SetService setService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addSet(@RequestBody SetAddResource set) {
        Integer setId = setService.addSet(
                set.getExerciseId(),
                set.getUserId(),
                set.getPerformedOn(),
                set.getWeight(),
                set.getRepetitions()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{set_id}")
                .buildAndExpand(setId).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<SetResource> getSets() {
        return jdbcTemplate.query("SELECT * FROM sets", (rs, rowNum) -> {
            return new SetResource(
                    rs.getLong("set_id"),
                    rs.getLong("exercise_id"),
                    rs.getLong("user_id"),
                    rs.getDate("performed_on"),
                    rs.getDouble("weight"),
                    rs.getInt("repetitions"),
                    rs.getDouble("estimated_1rm")
            );
        });
    }
}
