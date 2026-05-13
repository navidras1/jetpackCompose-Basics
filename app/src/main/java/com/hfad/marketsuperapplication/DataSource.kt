package com.hfad.marketsuperapplication

interface DataSource {
    fun getInfo(): String
    suspend fun getInfoSuspend(): String
}