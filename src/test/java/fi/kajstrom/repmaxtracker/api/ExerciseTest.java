package fi.kajstrom.repmaxtracker.api;

import fi.kajstrom.repmaxtracker.resources.AddGreeting;
import fi.kajstrom.repmaxtracker.resources.Exercise;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExerciseTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String makeUrl(String queryPath) {
        return "http://localhost:" + port + queryPath;
    }

    @Test
    public void getExercisesReturnsListOfExercises() throws Exception {
        ResponseEntity<Exercise[]> responseEntity = restTemplate.getForEntity(makeUrl("/exercises"), Exercise[].class);
        Exercise[] exercises = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();

        assertThat(statusCode.is2xxSuccessful()).isTrue();
        assertThat(exercises[0].getName()).isEqualToIgnoringCase("Deadlift");
    }
}
