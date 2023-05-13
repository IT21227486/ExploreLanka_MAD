package com.modles.explorehotel.util

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class HelperHotelsTest {
    lateinit var helper: HelperHotels

    @Before
    fun setUp() {
        helper = HelperHotels()
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
        val result = helper.isPallindroneHotel("hello")
        //Assert
        assertEquals(false, result)

    }

    @Test
    fun isPallindrone_inputString_level_expectedTrue() {

        //Arrange
        //val helper = Helper()
        //Act
        val result = helper.isPallindroneHotel("level")
        //Assert
        assertEquals(true, result)


    }
}

