package fi.kajstrom.repmaxtracker.api;

import fi.kajstrom.repmaxtracker.resources.ErrorResource;
import fi.kajstrom.repmaxtracker.resources.SetResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserSetsTest extends ApiTest {

    @Test
    public void getUserSetsShouldReturnSetsOnlyForSpecifiedUser() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        Date performedOn = cal.getTime();

        makeSetAddRequest(1, 2, performedOn, 100.0, 6);
        makeSetAddRequest(1, 1, performedOn, 100.0, 6);
        makeSetAddRequest(1, 2, performedOn, 100.0, 6);

        ResponseEntity<SetResource[]> responseEntity = restTemplate.getForEntity(makeUrl("/users/2/sets"), SetResource[].class);
        SetResource[] sets = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
        assertThat(sets).hasSize(2);
    }

    @Test
    public void getUserSetsShouldReturnBadRequestWithErrorMessageWhenAttemptingToRetrieveSetsOfANonExistingUser() {
        ResponseEntity<ErrorResource> responseEntity = restTemplate.getForEntity(makeUrl("/users/9/sets"), ErrorResource.class);
        ErrorResource error = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(400);
        assertThat(error.getErrorCode()).isEqualTo(2);
    }

    @Test
    public void deleteUserSetShouldReturn200OkWhenDeletingAnExistingSet() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        Date performedOn = cal.getTime();

        makeSetAddRequest(1, 2, performedOn, 100.0, 6);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                makeUrl("/users/2/sets/1"),
                HttpMethod.DELETE,
                null,
                Object.class
        );

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void deleteUserSetShouldReturnBadRequestAndErrorMessageWhenAttemptingToDeleteASetOfNonExistingUser() {
        ResponseEntity<ErrorResource> responseEntity = restTemplate.exchange(
                makeUrl("/users/9/sets/5"),
                HttpMethod.DELETE,
                null,
                ErrorResource.class
        );
        ErrorResource error = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(400);
        assertThat(error.getErrorCode()).isEqualTo(2);
    }
}
