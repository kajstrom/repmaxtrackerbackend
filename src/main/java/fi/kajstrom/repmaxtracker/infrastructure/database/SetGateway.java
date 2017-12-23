package fi.kajstrom.repmaxtracker.infrastructure.database;

import fi.kajstrom.repmaxtracker.domain.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class SetGateway {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SetGateway(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer addSet(Set set) {
        return 1;
    }
}
