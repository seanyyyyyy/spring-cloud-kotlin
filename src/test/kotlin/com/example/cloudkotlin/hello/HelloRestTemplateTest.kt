package com.example.cloudkotlin.hello

import com.example.cloudkotlin.CloudKotlinApplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(
        classes = [CloudKotlinApplication::class],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloRestTemplateTest(@Autowired var testRestTemplate: TestRestTemplate){

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Hello should return default message`() {
        val result = testRestTemplate
                .withBasicAuth("user","pass")
                .getForEntity<String>("/hello")

        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result.body).contains("Hello, World")
    }

    @Test
    fun `Assert article page title, content and status code`() {
        println(">> TODO")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}