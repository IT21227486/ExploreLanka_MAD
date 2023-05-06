package com.example.explorelanka.util

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class HelperPlaceTest {

    lateinit var helper: HelperPlace

    @Before
    fun setUp() {
        helper = HelperPlace()
    }

    @After
    fun tearDown() {
        println("After Every Test Case")
    }

    @Test
    fun isPallindrone() {

        //Arrange
        //val helper = Helper()
        //Act
        val result = helper.isPallindrone("hello")
        //Assert
        assertEquals(false, result)

    }

    @Test
    fun isPallindrone_inputString_level_expectedTrue() {

        //Arrange
        //val helper = Helper()
        //Act
        val result = helper.isPallindrone("level")
        //Assert
        assertEquals(true, result)

    }

}