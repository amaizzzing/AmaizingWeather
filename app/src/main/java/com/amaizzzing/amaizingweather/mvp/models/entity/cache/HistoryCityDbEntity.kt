package com.amaizzzing.amaizingweather.mvp.models.entity.cache

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CityDbEntity::class,
            parentColumns = ["idCity"],
            childColumns = ["idCity"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class HistoryCityDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val idCity: Long
)