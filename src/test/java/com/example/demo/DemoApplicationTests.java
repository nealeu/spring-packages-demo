package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @Autowired
    private WebTestClient webClient;

    private WebTestClient autenticatingWebClient;

    @Before
    public void init() {
        autenticatingWebClient = webClient.mutate()
                .filter(basicAuthentication("test-user", "tust-pazvord"))
                .build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void cannotAccessWithoutAuthentication() {
        webClient.get().uri("/packages").accept(MediaType.APPLICATION_JSON_UTF8).exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    public void canCheckHealthWithoutAuthentication() {
        webClient.get().uri("/actuator/health").accept(MediaType.APPLICATION_JSON_UTF8).exchange()
                .expectStatus().isOk();
    }

    @Test
    public void canGetListOfPackages() {
        autenticatingWebClient.get().uri("/packages").accept(MediaType.APPLICATION_JSON_UTF8).exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\"id\":1,\"name\":\"package name\",\"description\":\"package description\"}");
    }
}

