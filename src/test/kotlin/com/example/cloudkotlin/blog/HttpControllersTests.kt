package com.example.cloudkotlin.blog

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@WebMvcTest(ArticleController::class, UserController::class)
class HttpControllersTests (@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        val spring5Article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
        val spring43Article = Article("Spring Framework 4.3 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(spring5Article, spring43Article)
        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].author.login").value(juergen.login))
                .andExpect(jsonPath("\$.[0].slug").value(spring5Article.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(juergen.login))
                .andExpect(jsonPath("\$.[1].slug").value(spring43Article.slug))
    }

    @Test
    fun `List users`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        val smaldini = User("smaldini", "Stéphane", "Maldini")
        every { userRepository.findAll() } returns listOf(juergen, smaldini)
        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].login").value(juergen.login))
                .andExpect(jsonPath("\$.[1].login").value(smaldini.login))
    }

    //TODO
    fun `add user`() {

        val mockResponse = "{\n" +
                "    \"login\": \"user2\",\n" +
                "    \"firstname\": \"misus\",\n" +
                "    \"lastname\": \"woman\",\n" +
                "    \"description\": \"woman\",\n" +
                "    \"id\": 5\n" +
                "}"
        //every { userRepository.save(newUser) } returns mockResponse

        val body = """
            {
            	"login": "user2",
            	"firstname": "misus",
            	"lastname": "woman",
            	"description" : "woman"
            }
        """.trimIndent()

        mockMvc.post("/api/user/addUser/") {
            contentType = MediaType.APPLICATION_JSON
            content = body
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("{}") }
        }
    }
}