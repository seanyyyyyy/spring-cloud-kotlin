package com.example.cloudkotlin.hello

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @RequestMapping(
            path = ["/hello"]
    )
    @ResponseBody
    fun greeting():String {
        return "Hello, world!"
    }

}