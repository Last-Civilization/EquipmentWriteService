package com.lastcivilization.equipmentwriteservice.utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@TestConfiguration
class WireMockConfiguration {

    @Bean
    public WireMockServer wireMockServer(){
        WireMockServer wireMockServer = new WireMockServer(9561);
        mockItems(wireMockServer);
        return wireMockServer;
    }

    private void mockItems(WireMockServer wireMockServer) {
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/users/helmet"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"equipment\":1}")));
    }
}
