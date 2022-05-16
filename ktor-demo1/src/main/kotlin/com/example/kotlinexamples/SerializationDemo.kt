package com.example.kotlinexamples

import io.ktor.http.*
import kotlinx.serialization.Serializable
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.*
import kotlinx.html.*

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}

@Serializable
data class RequestDto(val user: String, val numberOfResults: Int)

@Serializable
data class ResultDto(val name: String, val success: Boolean)

@Serializable
data class RespDto(val someResults: List<ResultDto>)

fun Application.configureSerializationDemo() {
    routing {

        // http://localhost:8080/test-query-params?msg=Hi&repeat=4
        get("/test-query-params") {
            val msg = call.parameters["msg"] ?: "No message..."
            val repeat = call.parameters["repeat"]?.toIntOrNull() ?: 1

            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/test.css", type = "text/css")
                }
                body {
                    repeat(repeat) {
                        h1 { +"Msg $it: $msg" }
                    }

                }
            }
        }

        get("/test.css") {
            call.respondCss {
                body {
                    backgroundColor = Color.darkBlue
                    margin(20.px)
                }
                rule("h1") {
                    color = Color.white
                }
            }
        }

        // curl -X POST http://localhost:8080/post-json -H 'Content-Type: application/json' -d '{"user": "test", "numberOfResults": 6}'
        post("/post-json") {
            // För att få in en json-body kan vi göra:
            val reqDto = call.receive<RequestDto>()

            val results = (0..reqDto.numberOfResults).map {
                ResultDto("test $it", true)
            }

            // För att skicka tillbaks ett json-svar kan vi göra:
            call.respond(RespDto(results))
        }

    }
}