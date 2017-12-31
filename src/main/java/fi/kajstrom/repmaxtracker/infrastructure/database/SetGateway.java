package fi.kajstrom.repmaxtracker.infrastructure.database;

import fi.kajstrom.repmaxtracker.domain.Set;
import fi.kajstrom.repmaxtracker.infrastructure.database.rowmapper.SetRowMapper;
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
    private SetRowMapper setRowMapper;

    @Autowired
    public SetGateway(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.setRowMapper = new SetRowMapper();
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
        return jdbcTemplate.query("SELECT * FROM sets", setRowMapper);
    }

    public List<Set> getUserSets(long userId) {
        final String sql = "SELECT * FROM sets WHERE user_id = ? ORDER BY performed_on ASC";

        return jdbcTemplate.query(sql, new Object[]{userId}, setRowMapper);
    }

    public Set getSet(long setId) {
        try {
            return (Set) jdbcTemplate.queryForObject("SELECT * FROM sets WHERE set_id = ?",
                    new Object[]{setId},
                    setRowMapper);
        } catch (EmptyResultDataAccessException e) {
            //No result found with given set id.
            return null;
        }
    }

    public Boolean deleteUserSet(long userId, long setId) {
        final String sql = "DELETE FROM sets WHERE user_id = ? AND set_id = ?";
        Object[] params = new Object[] {userId, setId};

        int deleteCount = jdbcTemplate.update(sql, params);

        return deleteCount == 1;
    }
}
