package fi.kajstrom.repmaxtracker.api;

import fi.kajstrom.repmaxtracker.resources.SetAddResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

import java.net.URI;
import java.util.Date;

public class ApiTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    String makeUrl(String queryPath) {
        return "http://localhost:" + port + queryPath;
    }

    protected URI makeSetAddRequest(long exerciseId, long userId, Date performedOn, Double weight, Integer reperations) {
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
