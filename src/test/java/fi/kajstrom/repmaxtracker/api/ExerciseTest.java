package fi.kajstrom.repmaxtracker.api;

import fi.kajstrom.repmaxtracker.resources.ExerciseResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExerciseTest extends ApiTest {
    @Test
    public void getExercisesReturnsListOfExercises() throws Exception {
        ResponseEntity<ExerciseResource[]> responseEntity = restTemplate.getForEntity(makeUrl("/exercises"), ExerciseResource[].class);
        ExerciseResource[] exercises = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();

        assertThat(statusCode.is2xxSuccessful()).isTrue();
        assertThat(exercises[0].getName()).isEqualToIgnoringCase("Deadlift");
    }
}
