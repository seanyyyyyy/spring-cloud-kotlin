package com.example.cloudkotlin

import com.example.cloudkotlin.hello.HelloController
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
    fun givenNoExistingCoursesWhenGetCoursesThenRespondWithStatusOkAndEmptyArray() {

        given()
        .`when`()
            .get("/hello")
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body(containsString("Hello, world!"))
    }
}