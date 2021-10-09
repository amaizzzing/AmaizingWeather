package com.amaizzzing.amaizingweather

import org.junit.Assert
import org.junit.Test

class StringUtilsTest {
    @Test
    fun `correctTemp returns temp correct`() {
        Assert.assertEquals(
            "+2°C",
            StringUtils.correctTemp(2.0)
        )
    }

    @Test
    fun `correctTemp returns negative temp correct`() {
        Assert.assertEquals(
            "-16°C",
            StringUtils.correctTemp(-16.0)
        )
    }

    @Test
    fun `correctTemp returns temp invalid`() {
        Assert.assertNotEquals(
            "+2°C",
            StringUtils.correctTemp(-2.0)
        )
    }

    @Test
    fun `correctTemp returns temp with zero`() {
        Assert.assertNotEquals(
            "0°C",
            StringUtils.correctTemp(0.0)
        )
    }

    @Test
    fun `correctWindSpeed returns windSpeed correct`() {
        Assert.assertEquals(
            "5 м/с",
            StringUtils.correctWindSpeed(5.0)
        )
    }

    @Test
    fun `correctWindSpeed returns windSpeed invalid`() {
        Assert.assertNotEquals(
            "10",
            StringUtils.correctWindSpeed(10.0)
        )
    }

    @Test
    fun `correctHumidity returns humidity correct`() {
        Assert.assertEquals(
            "1 %",
            StringUtils.correctHumidity(1)
        )
    }

    @Test
    fun `correctHumidity returns humidity invalid`() {
        Assert.assertNotEquals(
            "10",
            StringUtils.correctHumidity(10)
        )
    }

}