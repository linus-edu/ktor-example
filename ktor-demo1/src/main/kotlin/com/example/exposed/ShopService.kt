package com.example.exposed

import ItemDto
import com.example.exposed.dbmodel.Item
import com.example.exposed.dbmodel.Item.inventory
import com.example.exposed.dbmodel.Item.name
import com.example.exposed.dbmodel.Item.price
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object ShopService {

    fun addItem(item: ItemDto) = transaction {
        transaction {
            Item.insertAndGetId {
                it[name] = item.name
                it[price] = item.price
                it[inventory] = item.inventory
            }
        }
    }

    fun getItems(): List<ItemDto> = transaction{
        Item.selectAll().map {
            ItemDto(
                it[name],
                it[price],
                it[inventory]
            )
        }
    }
}