package fi.kajstrom.repmaxtracker.infrastructure.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("userGateway")
public class UserGateway {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserGateway(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean userExists(long userId) {
        final String sql = "SELECT COUNT(user_id) AS user_cnt FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{userId},
                (rs,  rowNum) -> rs.getInt("user_cnt") == 1
        );
    }
}
