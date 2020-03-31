package com.example.cloudkotlin

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest(
        classes = arrayOf(CloudKotlinApplication::class),
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class GreetingRestTemplateTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun whenCalledShouldReturnHelloService() {
        val result = testRestTemplate
                .withBasicAuth("user","pass")
                .getForEntity("/greeting", String::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(result?.body, "Greetings, World")
    }
}