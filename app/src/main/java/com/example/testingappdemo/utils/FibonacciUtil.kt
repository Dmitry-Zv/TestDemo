package com.example.testingappdemo.utils

object FibonacciUtil {

    /**
     * Returns the n-th fibonacci number
     * They are defined like this:
     * fib(0) = 0
     * fib(1) = 1
     * fib(n) = fib(n-2) + fib(n-1)
     * */

    fun fib(n: Int): Long {
        if (n == 0 || n == 1) return n.toLong()
        return (fib(n = n - 2) + fib(n = n - 1))

    }
}