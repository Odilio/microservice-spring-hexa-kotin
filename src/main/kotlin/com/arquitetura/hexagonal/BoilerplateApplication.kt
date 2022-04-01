package com.arquitetura.hexagonal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@SpringBootApplication
@EnableDiscoveryClient
class BoilerplateApplication

fun main(args: Array<String>) {
    runApplication<BoilerplateApplication>(*args)
}


