package com.example.payment_for_explorelanka.payTest

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidatorTest {

    private val validator = Validator()

    @Test
    fun testIsValueGreaterOrEqualToZero() {
        assertTrue(validator.isValueGreaterOrEqualToZero(0))
        assertTrue(validator.isValueGreaterOrEqualToZero(5))
        assertFalse(validator.isValueGreaterOrEqualToZero(-1))
        assertFalse(validator.isValueGreaterOrEqualToZero(-5))
    }

    @Test
    fun testIsNumberOrString() {
        assertTrue(validator.isNumberOrString(0))
        assertTrue(validator.isNumberOrString(3.14))
        assertTrue(validator.isNumberOrString(1234567890))
        assertTrue(validator.isNumberOrString("0"))
        assertTrue(validator.isNumberOrString("3.14"))
        assertTrue(validator.isNumberOrString("1234567890"))
        assertFalse(validator.isNumberOrString(true))
        assertFalse(validator.isNumberOrString(false))
        assertFalse(validator.isNumberOrString("Hello, world!"))
    }

    @Test
    fun testIsDate() {
        assertTrue(validator.isDate("01/01/2000"))
        assertTrue(validator.isDate("31/12/2023"))
        assertFalse(validator.isDate("32/12/2023"))
        assertFalse(validator.isDate("31/13/2023"))
        assertFalse(validator.isDate("31-12-2023"))
        assertFalse(validator.isDate("2023/12/31"))
        assertFalse(validator.isDate("12/31/2023"))
        assertFalse(validator.isDate("2023-12-31"))
    }

}