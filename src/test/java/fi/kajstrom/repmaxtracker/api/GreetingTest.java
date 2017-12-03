package fi.kajstrom.repmaxtracker.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String makeUrl(String queryPath) {
        return "http://localhost:" + port + queryPath;
    }

    @Test
    public void greetingNameShouldDefaultToWorld() throws Exception {
        assertThat(this.restTemplate.getForObject(makeUrl("/greeting"),
                String.class)).contains("Hello, World");
    }

    @Test
    public void greetingNameShouldBeTakenFromNameParameter() throws Exception {
        assertThat(this.restTemplate.getForObject(makeUrl("/greeting?name=Kaj"),
                String.class)).contains("Hello, Kaj");
    }
}
