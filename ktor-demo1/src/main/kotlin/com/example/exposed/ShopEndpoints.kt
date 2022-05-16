package com.example.exposed

import ItemDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureShopEndpoints() {
    routing {

        get("/shop/items") {
            call.respond(ShopService.getItems())
        }

        // curl -X POST http://localhost:8080/shop/items -H 'Content-Type: application/json' -d '{"name": "test", "price": 60, "inventory": 5}'
        post("/shop/items") {
            val item = call.receive<ItemDto>()
            ShopService.addItem(item)
            call.respond(HttpStatusCode.Created)
        }
    }
}