package com.test.worldwether.data.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.test.worldwether.data.request.WeatherRequest
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import java.util.concurrent.TimeUnit

private const val BASE_API_WEATHER =  "https://api.worldweatheronline.com/premium/v1/weather"

class WeatherService {

    private val service: WorldWetherApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().apply {
            connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            readTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            addInterceptor(logging)
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_API_WEATHER)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        service = retrofit.create(WorldWetherApi::class.java)

    }

    fun getWeatherApi(request: WeatherRequest): Single<JsonObject> {
        val jsonObject = Gson().toJsonTree(
            request,
            TypeToken.getParameterized(request.javaClass).type
        ).asJsonObject
        return service.getWeatherApi(jsonObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}