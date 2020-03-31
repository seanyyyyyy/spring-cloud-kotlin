package com.example.cloudkotlin

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(HomeController::class)
class WebLayerTest {

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
                string(containsString("Hello, world!"))
            }
        }.andDo {
            print()
        }
    }
}