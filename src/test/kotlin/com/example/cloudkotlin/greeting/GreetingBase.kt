package com.example.cloudkotlin.greeting

import com.example.cloudkotlin.greeting.GreetingController
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GreetingBase {

    @Autowired
    lateinit var greetingController: GreetingController

    @BeforeEach
    fun initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(greetingController)
    }

    @Test
    fun checkRestController() {
        given()
        .`when`()
            .get("/greeting")
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body(containsString("Greetings, World"))
    }
}