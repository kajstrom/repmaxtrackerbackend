package fi.kajstrom.repmaxtracker.resources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addSet(@RequestBody SetAdd set) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSQL = "INSERT INTO sets (exercise_id, user_id, performed_on, weight, repetitions, estimated_1rm) VALUES(?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(insertSQL, new String[] {"set_id"});

            Date performedOn = new Date(set.getPerformedOn().getTime());

            ps.setLong(1, set.getExerciseId());
            ps.setLong(2, set.getUserId());
            ps.setDate(3, performedOn);
            ps.setDouble(4, set.getWeight());
            ps.setInt(5, set.getRepetitions());
            ps.setDouble(6, estimated1Rm(set.getWeight(), set.getRepetitions()));

            return ps;
        }, keyHolder);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{set_id}")
                .buildAndExpand(keyHolder.getKey()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Set> getSets() {
        return jdbcTemplate.query("SELECT * FROM sets", (rs, rowNum) -> {
            return new Set(
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

    /**
     * @todo Move this to a more proper location.
     * @param weight
     * @param reps
     * @return
     */
    private Double estimated1Rm(Double weight, Integer reps) {
        if (reps.equals(1)) {
            return weight;
        }

        return (weight * reps * 0.0333) + weight;
    }
}
