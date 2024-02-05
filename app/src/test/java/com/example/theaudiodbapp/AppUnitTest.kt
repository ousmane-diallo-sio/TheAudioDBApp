package com.example.theaudiodbapp

import org.junit.Assert
import org.junit.Test

class AppUnitTest {
    @Test
    fun `assert that 2 + 2 is 4`() {
        Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun `assert that 2 + 2 is not 5`() {
        Assert.assertNotEquals(5, 2 + 2)
    }

}