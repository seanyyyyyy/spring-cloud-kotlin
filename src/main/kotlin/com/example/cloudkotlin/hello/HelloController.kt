package com.example.cloudkotlin.hello

import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@RestController
class HelloController {

    val counter = AtomicLong()

    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue ="World") name:String)
    = Greeting(counter.incrementAndGet(),"Hello, $name")
}