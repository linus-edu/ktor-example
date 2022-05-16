package com.example

import com.example.exposed.SetupDatabase
import com.example.exposed.configureShopEndpoints
import com.example.kotlinexamples.configureCoroutineDemo
import com.example.kotlinexamples.configureSerializationDemo
import com.example.plugins.*
import io.ktor.server.application.*

// When starting like this application.conf is read
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

// We point this module out as a starting point in application.conf
fun Application.module() {
        // install(RateLimitPlugin)

        SetupDatabase.connectAndCreate()

        configureRouting()
        configureSockets()
        configureSerialization()
        configureTemplating()
        configureSecurity()

        configureCoroutineDemo()
        configureSerializationDemo()
        configureShopEndpoints()
}

//fun main() {
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
//        configureRouting()
//        ...
//    }.start(wait = true)
//}
