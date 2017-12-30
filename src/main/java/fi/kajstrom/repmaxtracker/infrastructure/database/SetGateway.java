package fi.kajstrom.repmaxtracker.infrastructure.database;

import fi.kajstrom.repmaxtracker.domain.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

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

    public List<Set> allSets() {
        return jdbcTemplate.query("SELECT * FROM sets", (rs, rowNum) -> {
            Set set =  new Set();

            set.setSetId(rs.getLong("set_id"));
            set.setExerciseId(rs.getLong("exercise_id"));
            set.setExerciseId(rs.getLong("user_id"));
            set.setPerformedOn(rs.getDate("performed_on"));
            set.setWeight(rs.getDouble("weight"));
            set.setRepetitions(rs.getInt("repetitions"));
            set.setEstimated1Rm(rs.getDouble("estimated_1rm"));

            return set;
        });
    }

    public List<Set> getUserSets(long userId) {
        final String sql = "SELECT * FROM sets WHERE user_id = ? ORDER BY performed_on ASC";

        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            Set set = new Set();

            set.setSetId(rs.getLong("set_id"));
            set.setExerciseId(rs.getLong("exercise_id"));
            set.setExerciseId(rs.getLong("user_id"));
            set.setPerformedOn(rs.getDate("performed_on"));
            set.setWeight(rs.getDouble("weight"));
            set.setRepetitions(rs.getInt("repetitions"));
            set.setEstimated1Rm(rs.getDouble("estimated_1rm"));

            return set;
        });
    }

    public Set getSet(long setId) {
        try {
            return (Set) jdbcTemplate.queryForObject("SELECT * FROM sets WHERE set_id = ?",
                    new Object[]{setId},
                    (rs, rowNum) -> {
                        Set set = new Set();

                        set.setSetId(rs.getLong("set_id"));
                        set.setExerciseId(rs.getLong("exercise_id"));
                        set.setExerciseId(rs.getLong("user_id"));
                        set.setPerformedOn(rs.getDate("performed_on"));
                        set.setWeight(rs.getDouble("weight"));
                        set.setRepetitions(rs.getInt("repetitions"));
                        set.setEstimated1Rm(rs.getDouble("estimated_1rm"));

                        return set;
                    });
        } catch (EmptyResultDataAccessException e) {
            //No result found with given set id.
            return null;
        }
    }
}
