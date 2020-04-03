package com.example.cloudkotlin.hello

import com.example.cloudkotlin.CloudKotlinApplication
import org.assertj.core.api.Assertions.assertThat
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

    @Test
    fun `Hello should return default message`() {
        val result = testRestTemplate
                .withBasicAuth("user","pass")
                .getForEntity<String>("/hello")

        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result.body).contains("Hello, world!")
    }

}