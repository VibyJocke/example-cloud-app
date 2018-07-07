package com.lahtinen.cloud.service.frontend.port;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lahtinen.cloud.service.frontend.infrastructure.FrondEndApp;
import com.lahtinen.cloud.service.frontend.infrastructure.FrontEndAppConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class V1IntegrationTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @ClassRule
    public static final DropwizardAppRule<FrontEndAppConfiguration> RULE =
            new DropwizardAppRule<>(FrondEndApp.class, ResourceHelpers.resourceFilePath("config.yml"));

    @Test
    public void getPosts() throws Exception {
        Response response = client()
                .path("post")
                .request(APPLICATION_JSON_TYPE)
                .get();

        assertThat(toJson(response).size(), is(2));
    }

    private JsonNode toJson(Response body) throws IOException {
        return MAPPER.readTree(body.readEntity(String.class));
    }

    private WebTarget client() {
        return ClientBuilder.newClient()
                .target("http://localhost:" + RULE.getLocalPort())
                .path("v1");
    }
}