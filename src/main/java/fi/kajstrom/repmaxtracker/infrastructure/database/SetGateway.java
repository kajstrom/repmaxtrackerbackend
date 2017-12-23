package fi.kajstrom.repmaxtracker.infrastructure.database;

import fi.kajstrom.repmaxtracker.domain.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;

@Component("setGateway")
public class SetGateway {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SetGateway(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer addSet(Set set) {
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
            ps.setDouble(6, set.getEstimated1Rm());

            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
