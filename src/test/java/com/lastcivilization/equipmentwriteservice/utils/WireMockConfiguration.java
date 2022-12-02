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

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer(){
        WireMockServer wireMockServer = new WireMockServer(9561);
        mockUsers(wireMockServer);
        mockItems(wireMockServer);
        return wireMockServer;
    }

    private void mockItems(WireMockServer wireMockServer) {
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/items/1"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"type\":\"HELMET\" }")));
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/items/2"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"type\":\"ARMOR\" }")));
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/items/3"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"type\":\"SHOES\" }")));
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/items/4"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"type\":\"PANTS\" }")));
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/items/5"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"type\":\"WEAPON\" }")));
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/items/6"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"type\":\"SHIELD\" }")));
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/items/1/exists"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("true")));
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/items/2/exists"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("true")));
    }

    private void mockUsers(WireMockServer wireMockServer) {
        wireMockServer.stubFor(get(WireMock.urlEqualTo("/users/test"))
                .willReturn(aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"equipment\": 1 }")));
    }
}
