package com.example.cloudkotlin.testingrestdocs

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
class RestdocsHelloControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun shouldReturnDefaultMessage() {
        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk)
                .andExpect(content().string(containsString("Greetings, World!")))
                .andDo(document(
                        "home",
                        responseFields(
                            fieldWithPath("message").description("The welcome message for the user.")
                        )
                ))
    }


    /* MockMvc Kotlin DSL does not generate snippets for some reason
    @Test
    fun shouldReturnDefaultMessageKotlinDSL() {
        mockMvc.get("/hello")
        .andExpect {
            status { isOk }
            content {
                string(containsString("Hello, world!"))
            }
        }.andDo {
            print()
            document("home")
        }
    }
     */
}