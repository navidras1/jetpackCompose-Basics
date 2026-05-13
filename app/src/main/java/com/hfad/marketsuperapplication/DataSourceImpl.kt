package com.hfad.marketsuperapplication

import org.koin.dsl.module

class DataSourceImpl : DataSource {
    override fun getInfo(): String = "Local data from DataSourceImpl"

    override suspend fun getInfoSuspend(): String {// Simulate IO; in real life this could be a network/db call
        kotlinx.coroutines.delay(1000)
        return "IO data from DataSourceImpl"}
    }

    val appModule = module {
        single<DataSource> { DataSourceImpl() }
    }
