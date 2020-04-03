package com.example.cloudkotlin.hello

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(HelloController::class)
class HelloWebLayerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun shouldReturnDefaultMessage() {
        mockMvc.get("/hello") {
            accept(MediaType.APPLICATION_JSON)
        }.andExpect {
            status { isOk }
            content {
                contentType(MediaType.APPLICATION_JSON)
                string(containsString("Hello, World"))
            }
        }.andDo {
            print()
        }
    }
}