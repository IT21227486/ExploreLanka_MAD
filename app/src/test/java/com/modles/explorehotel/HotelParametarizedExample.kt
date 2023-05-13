package com.modles.explorehotel

import com.modles.explorehotel.util.HelperHotels
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class HotelParameterizedExample(val input: String, val expectedValue: Boolean) {

    @Test
    fun test() {
        val helper = HelperHotels()
        val result = helper.isPallindroneHotel(input)
        assertEquals(expectedValue, result)
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} is pallindrone - {1}")
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf("hello", false),
                arrayOf("level", true),
                arrayOf("a", true),
                arrayOf("", true)
            )

        }
    }
}