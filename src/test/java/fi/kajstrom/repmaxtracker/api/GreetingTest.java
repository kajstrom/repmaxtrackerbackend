package fi.kajstrom.repmaxtracker.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import fi.kajstrom.repmaxtracker.resources.AddGreeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingTest extends ApiTest {
    @Test
    public void addGreeting() throws Exception {
        HttpEntity<AddGreeting> request = new HttpEntity<>(new AddGreeting("Kaj"));
        URI location = this.restTemplate.postForLocation(makeUrl("/greetings"), request);
        System.out.println(location);
        assertThat(location).isNotNull();
    }
}
