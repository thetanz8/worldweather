package com.test.worldwether.data.service

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET

interface WorldWetherApi {

    @GET("")
    fun getWeatherApi(@Body jsonObject: JsonObject): Single<JsonObject>

}