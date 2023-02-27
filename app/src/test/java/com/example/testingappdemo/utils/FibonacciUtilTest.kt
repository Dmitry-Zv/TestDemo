package com.example.testingappdemo.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FibonacciUtilTest{


    @Test
    fun `if n equals to 0 return 0L`(){
        val result = FibonacciUtil.fib(n = 0)
        assertThat(result).isEqualTo(0L)

    }

    @Test
    fun `if n equals to 1 return 1L`(){
        val result = FibonacciUtil.fib(n = 1)
        assertThat(result).isEqualTo(1L)

    }

    @Test
    fun `if n greater than 1 return fib(n-2) + fib(n-1)`(){
        val result = FibonacciUtil.fib(n = 4)
        assertThat(result).isEqualTo(3L)

    }





}