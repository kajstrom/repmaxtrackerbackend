package fi.kajstrom.repmaxtracker.integration.infrastructure.database;

import com.sun.org.apache.xpath.internal.operations.Bool;
import fi.kajstrom.repmaxtracker.Application;
import fi.kajstrom.repmaxtracker.infrastructure.database.UserGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserGatewayTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void userExistsWhenCalledWithANonExistingUserIdReturnsFalse() {
        UserGateway userGateway = makeUserGateway(jdbcTemplate);

        Boolean userExists = userGateway.userExists(3);

        assertThat(userExists).isFalse();
    }

    @Test
    public void userExistsWhenCalledWithAnExistingUserIdReturnsTrue() {
        UserGateway userGateway = makeUserGateway(jdbcTemplate);

        Boolean userExists = userGateway.userExists(2);

        assertThat(userExists).isTrue();
    }

    private UserGateway makeUserGateway(JdbcTemplate jdbcTemplate) {
        return new UserGateway(jdbcTemplate);
    }
}
