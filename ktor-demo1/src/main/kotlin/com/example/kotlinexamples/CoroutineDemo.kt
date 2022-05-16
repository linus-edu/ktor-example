package com.example.kotlinexamples

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay

fun foo() {
    // foo är inte en suspend-funktion, så vi kan inte anropa sleep
    // delay(1000L);
}

// En suspend-funktion kan däremot anropa en annan
suspend fun sleepOneSecond() = delay(1000L)

fun Application.configureCoroutineDemo() {

    routing {

        get("/coroutine-demo") {
            call.respondTextWriter {
                for (i in 0..5) {
                    write("chunk: $i\n")
                    flush()
                    sleepOneSecond()
                }
            }
        }


//        Send a HTTP chunk to /listen when /send is called. (Not a good example, incorrect coroutine handling)
//        val channel = Channel<String>()
//
//        get("/listen") {
//            call.respondTextWriter {
//                while(true) {
//                    write(channel.receive() + "\n")
//                    flush()
//                }
//            }
//        }
//
//        get("/send") {
//            val msg = call.parameters["msg"] ?: "hello"
//            channel.send(msg)
//        }

    }
}