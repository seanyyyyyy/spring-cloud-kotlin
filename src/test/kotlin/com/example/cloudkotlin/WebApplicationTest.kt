package com.example.cloudkotlin

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class WebApplicationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun shouldReturnHelloMessage() {
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

    @Test
    fun shouldReturnGreetingMessage() {
        mockMvc.get("/greeting") {
            accept(MediaType.APPLICATION_JSON)
        }.andExpect {
            status { isOk }
            content {
                contentType(MediaType.APPLICATION_JSON)
                string(containsString("Greetings, World"))
            }
        }.andDo {
            print()
        }
    }
}