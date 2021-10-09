package com.amaizzzing.amaizingweather

import org.junit.Assert
import org.junit.Test
import java.util.*

class DateUtilsTest {
    @Test
    fun `millisToDateString returns date correct`() {
        Assert.assertEquals(
            "24.12",
            DateUtils.millisToDateString(1545677965000)
        )
    }

    @Test
    fun `millisToDateString returns date invalid`() {
        Assert.assertNotEquals(
            "04.03",
            DateUtils.millisToDateString(1580756400000)
        )
    }

    @Test
    fun `millisToDateString returns date with zero date`() {
        Assert.assertEquals(
            "01.01",
            DateUtils.millisToDateString(0)
        )
    }

    @Test
    fun `millisToTimeString returns time correct`() {
        Assert.assertEquals(
            "16:39",
            DateUtils.millisToTimeString(1545651565000)
        )
    }

    @Test
    fun `millisToTimeString valid format`() {
        Assert.assertTrue(DateUtils.millisToTimeString(1545651565000).contains(":"))
    }

    @Test
    fun `millisToTimeString returns time invalid`() {
        Assert.assertNotEquals(
            "01:15",
            DateUtils.millisToTimeString(1545651565000)
        )
    }

    @Test
    fun `millisToTimeString returns time with zero date`() {
        val zoneOffset = Calendar.getInstance().get(Calendar.ZONE_OFFSET) / 3600 / 1000
        val res = if(zoneOffset < 10) "0$zoneOffset" else "$zoneOffset"

        Assert.assertEquals(
            "$res:00",
            DateUtils.millisToTimeString(0)
        )
    }

    @Test
    fun `atEndOfDay returns millis correct`() {
        Assert.assertEquals(
            1545677999999,
            DateUtils.atEndOfDay(1545649225000)
        )
    }
    @Test
    fun `atEndOfDay returns millis invalid`() {
        Assert.assertNotEquals(
            1545677999000,
            DateUtils.atEndOfDay(1545649225000)
        )
    }

    @Test
    fun `atStartOfDay returns millis correct`() {
        Assert.assertEquals(
            1545591600000,
            DateUtils.atStartOfDay(1545649225000)
        )
    }
    @Test
    fun `atStartOfDay returns millis invalid`() {
        Assert.assertNotEquals(
            1545591600999,
            DateUtils.atStartOfDay(1545649225000)
        )
    }
}