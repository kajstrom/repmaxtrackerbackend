package fi.kajstrom.repmaxtracker.infrastructure.database.rowmapper;

import fi.kajstrom.repmaxtracker.domain.Set;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SetRowMapper implements RowMapper {
    @Override
    public Set mapRow(ResultSet rs, int i) throws SQLException {
        Set set = new Set();

        set.setSetId(rs.getLong("set_id"));
        set.setExerciseId(rs.getLong("exercise_id"));
        set.setExerciseId(rs.getLong("user_id"));
        set.setPerformedOn(rs.getDate("performed_on"));
        set.setWeight(rs.getDouble("weight"));
        set.setRepetitions(rs.getInt("repetitions"));
        set.setEstimated1Rm(rs.getDouble("estimated_1rm"));

        return set;
    }
}
