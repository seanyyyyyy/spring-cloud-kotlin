package com.example.cloudkotlin.greeting

import org.springframework.stereotype.Service

@Service
class GreetingService {

    fun greet(): String {
        return "Greetings, World"
    }
}