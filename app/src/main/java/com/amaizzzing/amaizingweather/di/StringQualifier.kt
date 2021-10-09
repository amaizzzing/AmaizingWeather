package com.amaizzzing.amaizingweather.di

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class StringQualifier(val value: String = "")