package com.example.cloudkotlin.hello

import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HelloBase {

    @Autowired
    lateinit var helloController: HelloController

    @BeforeEach
    fun initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(helloController)
    }

    @Test
    fun checkRestController() {
        given()
        .`when`()
            .get("/hello")
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body(containsString("Hello, world!"))
    }
}