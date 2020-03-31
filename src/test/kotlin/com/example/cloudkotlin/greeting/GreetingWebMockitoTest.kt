package com.example.cloudkotlin.greeting

import com.example.cloudkotlin.greeting.GreetingController
import com.example.cloudkotlin.greeting.GreetingService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.hamcrest.CoreMatchers.containsString
import org.mockito.Mockito


@WebMvcTest(GreetingController::class)
class GreetingWebMockitoTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var mockitoService: GreetingService

    @Test
    fun greetingShouldReturnMessageFromServiceMockito() {
        Mockito.`when`(mockitoService.greet()).thenReturn("Greetings, Mockito")

        mockMvc.get("/greeting") {

        }.andExpect{
            status { isOk }
            content { string(containsString("Greetings, Mockito")) }
        }.andDo {
            print()
        }
    }
}