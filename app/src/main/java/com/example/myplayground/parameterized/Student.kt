package com.example.myplayground.parameterized

class Student(
    val name: String,
    private val score: Int
) {
    fun getGrade(): String {
        return when (score) {
            in 31..50 -> "C"
            in 51..60 -> "B"
            in 61..75 -> "A"
            in 76..100 -> "A+"
            else -> "F"
        }
    }
}