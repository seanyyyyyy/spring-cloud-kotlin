package com.example.cloudkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CloudKotlinApplication

fun main(args: Array<String>) {
	runApplication<CloudKotlinApplication>(*args)
}
