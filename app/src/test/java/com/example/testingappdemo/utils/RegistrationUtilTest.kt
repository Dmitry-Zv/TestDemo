package com.example.testingappdemo.utils


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{


    @Test
    fun `empty user name returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Dmitry",
            "123",
            "123"
        )

        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }


    @Test
    fun `empty password returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Dmitry",
            "",
            ""
        )

        assertThat(result).isFalse()
    }


    @Test
    fun `password was repeated incorrectly returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Dmitry",
            "123",
            "23ad"
        )

        assertThat(result).isFalse()
    }


    @Test
    fun `passwords contains less than 2 digits returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Dmitry",
            "ae2",
            "ae2"
        )

        assertThat(result).isFalse()
    }
}