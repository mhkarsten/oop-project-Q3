package server;

import client.service.UrlEndPoints;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.DefaultUriBuilderFactory;
import server.model.DietEmission;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@Import(TestTemplateConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmissionControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Before
    public void setup() {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));
    }

    @Test
    void energyEmission() {
    }

    @Test
    void flightEmissionNullTest1() {
        HashMap parameters = new HashMap();
        parameters.put("startingPort", null);
        parameters.put("endingPort", null);
        DietEmission emission = restTemplate.postForObject(UrlEndPoints.Emission.URL_FLIGHT, new HttpEntity<>(parameters), DietEmission.class);
        assertNull(emission);
    }
    @Test
    void flightEmissionNullTest2() {
        HashMap parameters = new HashMap();
        parameters.put("startingPort", null);
        parameters.put("endingPort", "DET");
        DietEmission emission = restTemplate.postForObject(UrlEndPoints.Emission.URL_FLIGHT, new HttpEntity<>(parameters), DietEmission.class);
        assertNull(emission);
    }
    @Test
    void flightEmissionNullTest3() {
        HashMap parameters = new HashMap();
        parameters.put("startingPort", "DET");
        parameters.put("endingPort", null);
        DietEmission emission = restTemplate.postForObject(UrlEndPoints.Emission.URL_FLIGHT, new HttpEntity<>(parameters), DietEmission.class);
        assertNull(emission);
    }
    //This one was especially lacking in the coverage
    @Test
    void dietEmissionTest() {
        HashMap parameters = new HashMap();
        parameters.put("fishShare", 1.0f);
        parameters.put("redMeatShare", 1.0f);
        parameters.put("poultryShare", 1.0f);
        parameters.put("size", 10);
        DietEmission emission = restTemplate.postForObject(UrlEndPoints.Emission.URL_DIET, new HttpEntity<>(parameters), DietEmission.class);
        assertNotNull(emission);
    }
}