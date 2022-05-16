package com.example.kotlinexamples

// En hjälpfunktion så som vi kan skriva den i Java
fun parseList(s: String): List<String> {
    return s.split(",")
}

// Vi skriver funktionen på en typ, så kommer den vara tillgänglig som en metod, om vi har importerat den hör funktionen
// I funktionen kommer this vara objektet vi anropar metoden på
//fun String.parseList(): List<String> {
//    return this.split(",")
//}

// this behövs inte anges
//fun String.parseList(): List<String> {
//    return split(",")
//}

// Vi kan skippa parenteserna och return
// fun String.parseList() = split(",")

fun main() {

    val list = parseList("A,B,C,D")
    // val list = "A,B,C,D".parseList()

    for ((idx, s) in list.withIndex()) {
        println("$idx: $s")
    }

}