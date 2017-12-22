package fi.kajstrom.repmaxtracker.api;

import fi.kajstrom.repmaxtracker.resources.SetResource;
import fi.kajstrom.repmaxtracker.resources.SetAddResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SetTest extends ApiTest {
    @Test
    public void postSet() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 21);
        Date performedOn = cal.getTime();

        URI location = makeSetAddRequest(1, 1, performedOn, 100.0, 5);

        assertThat(location).hasPath("/sets/1");
    }

    @Test
    public void getSets() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 6);
        Date performedOn = cal.getTime();

        makeSetAddRequest(1, 1, performedOn, 100.0, 6);
        makeSetAddRequest(2, 1, performedOn, 107.5, 5);

        ResponseEntity<SetResource[]> responseEntity = restTemplate.getForEntity(makeUrl("/sets"), SetResource[].class);
        SetResource[] sets = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
        assertThat(sets).hasSize(2);
    }

    private URI makeSetAddRequest(long exerciseId, long userId, Date performedOn, Double weight, Integer reperations) {
        SetAddResource set = new SetAddResource(
                exerciseId,
                userId,
                performedOn,
                weight,
                reperations
        );

        HttpEntity<SetAddResource> request = new HttpEntity<>(set);
        return restTemplate.postForLocation(makeUrl("/sets"), request);
    }
}
