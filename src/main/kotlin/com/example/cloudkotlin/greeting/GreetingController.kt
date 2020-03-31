package com.example.cloudkotlin.greeting

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController(val service: GreetingService) {

    /** Java constructors are not needed as Kotlin can put them inline with class
    lateinit var service: GreetingService
    fun GreetingController(service: GreetingService ) { this.service = service }
    **/

    @GetMapping("/greeting")
    fun helloKotlinService(): String {
        return service.greet()
    }


}