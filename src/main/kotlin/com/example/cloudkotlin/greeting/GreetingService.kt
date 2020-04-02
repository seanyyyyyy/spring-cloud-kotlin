package com.example.cloudkotlin.greeting

import org.springframework.stereotype.Service

@Service
class GreetingService {

    /*
    fun greeting(): Map<String,Object> {
        return Collections.singletonMap("message", "Greetings, world!")
    }
     */

    fun greeting(): Map<String,String> {
        return mapOf("message" to "Greetings, World!")
    }
}