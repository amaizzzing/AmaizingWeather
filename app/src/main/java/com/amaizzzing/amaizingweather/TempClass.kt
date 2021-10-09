package com.amaizzzing.amaizingweather

data class TempClass(val temp: Int = 0) {
    fun getSomeList(from: Int, to: Int): List<Int> {
        return (from..to).toList()
    }

    fun getNullString(number: Int): String? {
        return if(number < 0) null else "some string"
    }

    fun copyTemp(): TempClass {
        return this.copy()
    }

    fun getInstance(): TempClass = this
}