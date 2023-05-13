package com.example.explorelanka.util

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class UserTest {

    lateinit var User: HelperPlace

    @Before
    fun setUp() {
        User = HelperPlace()
    }

    @After
    fun tearDown() {
        println("After Every Test Case")
    }

    @Test
    fun isPall() {

        //Arrange
        //val helper = Helper()
        //Act
        val result = User.isPallindrone("hello")
        //Assert
        assertEquals(false, result)

    }

    @Test
    fun isPall_inputString_level_expectedTrue() {

        //Arrange
        //val helper = Helper()
        //Act
        val result = User.isPallindrone("level")
        //Assert
        assertEquals(true, result)

    }
}