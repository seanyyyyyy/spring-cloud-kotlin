package com.example.cloudkotlin.greeting

import com.example.cloudkotlin.greeting.GreetingController
import com.example.cloudkotlin.greeting.GreetingService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.hamcrest.CoreMatchers.containsString
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean


@WebMvcTest(GreetingController::class)
class GreetingWebMockkTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var service: GreetingService

    @TestConfiguration
    class GreetingControllerTestConfig {
        @Bean
        fun service() = mockk<GreetingService>()
    }

    @Test
    fun greetingShouldReturnMessageFromServiceMockk() {
        every { service.greeting() } returns mapOf("message" to "Greetings, Mockk")

        mockMvc.get("/greeting") {

        }.andExpect{
            status { isOk }
            content { string(containsString("Greetings, Mockk")) }
        }.andDo {
            print()
        }
    }
}