package com.example.testingappdemo.utils

object CheckBracesUtil {


    fun checkBraces(text: String): Boolean {
        var number = 0
//        text.first {
//            it == ')'
//        }
        return text.count { it == '('} == text.count{it == ')'}
//        text.forEachIndexed { index, c ->
//            if (c == ')') number = index
//
//        }
    }
}