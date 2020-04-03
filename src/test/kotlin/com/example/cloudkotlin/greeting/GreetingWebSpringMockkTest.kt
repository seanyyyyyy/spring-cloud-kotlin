package com.example.cloudkotlin.greeting

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.hamcrest.CoreMatchers.containsString

@WebMvcTest(GreetingController::class)
class GreetingWebSpringMockkTest(@Autowired val mockMvc: MockMvc)  {

    @MockkBean
    private lateinit var greetingController: GreetingController

    @Test
    fun greetingShouldReturnMessageFromServiceMockk() {
        every { greetingController.helloKotlinService() } returns mapOf("message" to "Greetings, SpringMockk")

        mockMvc.get("/greeting") {
        }.andExpect{
            status { isOk }
            content { string(containsString("Greetings, SpringMockk")) }
        }.andDo {
            print()
        }
    }
}