package fi.kajstrom.repmaxtracker.api;

import fi.kajstrom.repmaxtracker.resources.SetAdd;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SetTest extends ApiTest {
    @Test
    public void addSet() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 21);
        Date performedOn = cal.getTime();

        SetAdd set = new SetAdd(
            1,
            1,
             performedOn,
            100.0,
            5
        );

        HttpEntity<SetAdd> request = new HttpEntity<>(set);
        URI location = this.restTemplate.postForLocation(makeUrl("/sets"), request);
        System.out.println(location);
        assertThat(location).isNotNull();
    }
}
