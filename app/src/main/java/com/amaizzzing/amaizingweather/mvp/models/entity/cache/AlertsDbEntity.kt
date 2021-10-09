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
class AlertsDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val senderName: String,
    val event: String,
    val start: Long,
    val end: Long,
    val description: String,

    val idCity: Long
)