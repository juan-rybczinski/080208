package com.fpinkotlin.recursion.exercise04

fun <T> makeString(list: List<T>, delim: String): String {
    tailrec fun makeString_(list: List<T>, acc: String): String =
            when {
                list.isEmpty() -> acc
                acc.isEmpty() -> makeString_(list.tail(), "${list.head()}")
                else -> makeString_(list.tail(), "$acc$delim${list.head()}")
            }
    return makeString_(list, "")
}

fun <T> List<T>.head(): T =
        if (this.isEmpty())
            throw IllegalArgumentException("head called on empty list")
        else
            this[0]

fun <T> List<T>.tail(): List<T> =
        if (this.isEmpty())
            throw IllegalArgumentException("tail called on empty list")
        else
            this.subList(1, this.size)
