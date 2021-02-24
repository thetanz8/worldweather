package com.test.worldwether.data.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherRequest (
    @SerializedName("q") var q: String? = "",
    @SerializedName("extra") var extra: String? = null,
    @SerializedName("num_of_days") var num_of_days: Int? = 0,
    @SerializedName("date") var date: String? = null,
    @SerializedName("date_format") var date_format: String? = null,
    @SerializedName("fx") var fx: String? = null,
    @SerializedName("cc") var cc: String? = null,
    @SerializedName("mca") var mca: String? = null,
    @SerializedName("fx24") var fx24: String? = null,
    @SerializedName("includelocation") var includelocation: String? = null,
    @SerializedName("tp") var tp: String? = null,
    @SerializedName("format") var format: String? = null,
    @SerializedName("show_comments") var show_comments: String? = null,
    @SerializedName("callback") var callback: String? = null,
    @SerializedName("key") var key: String? = null,
    @SerializedName("showlocaltime") var showlocaltime: String? = null,
    @SerializedName("showmap") var showmap: String? = null,
    @SerializedName("lang") var lang: String? = null,
    @SerializedName("aqi") var aqi: String? = null,
    @SerializedName("alerts") var alerts: String? = null
): Parcelable