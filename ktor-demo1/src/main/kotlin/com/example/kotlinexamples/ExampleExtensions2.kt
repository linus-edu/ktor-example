package com.example.kotlinexamples

fun Collection<Int>.product() = this.reduce { acc, i -> acc * i }

fun <T> T.addToList(list: MutableList<T>) = list.add(this)

// Generics funkar som det ska
fun main() {

    val intList = listOf(1, 2, 3)
    val intSet = setOf(4, 5, 6)
    val stringList = listOf("A", "B", "C")
    println(intList.product())
    println(intSet.product())
    // stringList.product() // Finns inte

    val listToAddTo = mutableListOf<String>()
    "Ett".addToList(listToAddTo)
    "Två".addToList(listToAddTo)
    println(listToAddTo)
    // 4.addToList(listToAddTo) // Finns inte
}