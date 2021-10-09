package com.amaizzzing.amaizingweather

import org.junit.Assert
import org.junit.Test

class TempClassTests {
    private val tempList: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5)
    private val tempClass = TempClass()

    @Test
    fun `getSomeArray return list correct`() {
        Assert.assertArrayEquals(
            tempList.toArray(),
            tempClass.getSomeList(1, 5).toTypedArray()
        )
    }

    @Test
    fun `getNullString return null`() {
        Assert.assertNull(tempClass.getNullString(-5))
    }

    @Test
    fun `getNullString return not null`() {
        Assert.assertNotNull(tempClass.getNullString(1))
    }

    @Test
    fun `copyTemp return other object`() {
        Assert.assertNotSame(tempClass.copyTemp(), tempClass)
    }

    @Test
    fun `return same object`() {
        Assert.assertSame(tempClass.getInstance(), tempClass)
    }
}