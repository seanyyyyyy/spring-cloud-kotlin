package com.example.cloudkotlin.testingrestdocs

import com.example.cloudkotlin.blog.User
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class RestdocsHelloControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun shouldReturnDefaultMessage() {
        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk)
                .andExpect(content().string(containsString("Greetings, World!")))
                .andDo(document(
                        "greeting",
                        responseFields(
                            fieldWithPath("message").description("The welcome message for the user.")
                        )
                ))
    }

    @Test
    fun `List users`() {
        val smaldini = User("smaldini", "Stéphane", "Maldini")

        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].login").value(smaldini.login))
                .andDo(
                    document("list-users")
                )
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