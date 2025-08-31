package com.test.kotlinapptest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinAppTestApplication

fun main(args: Array<String>) {
	runApplication<KotlinAppTestApplication>(*args)
}
