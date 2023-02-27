package com.example.testingappdemo.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CheckBracesUtilTest{

    @Test
    fun `if string contains counts of ( not th same as )  return false`(){
        val result = CheckBracesUtil.checkBraces("((a + b)))")
        assertThat(result).isFalse()
    }


//    @Test
//    fun `if string start with ) return false`(){
//        val result = CheckBracesUtil.checkBraces(")a+b(")
//        assertThat(result).isFalse()
//
//    }

}