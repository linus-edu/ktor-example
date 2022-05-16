package com.example.pluginexample

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*

/**
 * A very bad rate limit plugin.
 * A more useful one should use something like a sliding window.
 */
val RateLimitPlugin = createApplicationPlugin(name = "RateLimitPlugin") {
    println("Installed RateLimitPlugin")

    val callerToLastRequestTime = mutableMapOf<String, Long>()

    onCall { call ->
        val caller = call.request.origin.remoteHost // TODO This might not be the client host
        val now = System.currentTimeMillis()
        val msSinceLastRequest = now - callerToLastRequestTime.getOrDefault(caller, 0)

        // Reject the request if last request was too recent
        if (msSinceLastRequest < 1000) {
            println("RateLimitPlugin: Rejected request from $caller")
            call.respond(HttpStatusCode.TooManyRequests, "Calm down!")
        }

        callerToLastRequestTime[caller] = now
    }
}