package com.example.cloudkotlin.hello

import com.example.cloudkotlin.hello.HelloController
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HelloControllerSmokeTest {

    @Autowired
    lateinit var controller: HelloController

    @Test
    fun contexLoads() {
        //assertThat(controller).isNotNull
        assertNotNull(controller)
    }

}