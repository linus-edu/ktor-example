package com.example.exposed.dbmodel

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Item : IntIdTable("item") {
    val name: Column<String> = text("name")
    val price: Column<Int> = integer("price")
    val inventory: Column<Int> = integer("inventory")
}