package com.example.cloudkotlin.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Blog</h1>", "Reactor")
    }

    @Test
    fun `Assert article page title, content and status code`() {
        val title = "Reactor Aluminium has landed"
        val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(title, "Lorem ipsum", "dolor sit amet")
    }

    @Test
    fun `Assert add new user`() {
        val newUser = User("mainlogin1","Mister", "Man", "the main man")
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request: HttpEntity<User> = HttpEntity(newUser, headers)
        val entity = restTemplate.postForEntity<String>("/api/user/addUser", request, String::class)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("mainlogin1", "Mister", "Man", "the main man")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }

}