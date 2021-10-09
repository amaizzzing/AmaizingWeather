package com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.weather

import com.google.gson.annotations.Expose

data class AlertResponse(
        @Expose var senderName: String,
        @Expose var event: String,
        @Expose var start: Long,
        @Expose var end: Long,
        @Expose var description: String
)