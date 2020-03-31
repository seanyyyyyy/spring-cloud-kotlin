package com.example.cloudkotlin

import org.springframework.stereotype.Service

@Service
class GreetingService {

    fun greet(): String {
        return "Greetings, World"
    }
}