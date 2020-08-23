package com.gintaras.luminor.payments.web;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *  Tests rest endpoint.
 *
 * @created 04/08/2020 - 14:03
 * @author gintaras
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebHttpTest {

    @LocalServerPort
    private int port;


    @Test
    public void shouldReturnJsonObject() throws Exception {
        Response result = given().contentType("application/json")
                .when().get("http://localhost:" + port + "/payments")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .extract()
                .as(Response.class);
        assertThat(result).isNotNull();

    }
}
