package com.example.cloudkotlin

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SmokeTest {

    @Autowired
    lateinit var controller: HomeController

    @Test
    fun contexLoads() {
        //assertThat(controller).isNotNull
        assertNotNull(controller)
    }

}