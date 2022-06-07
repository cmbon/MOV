package com.duriansong.mov.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Plays (
    var nama:String ? = "",
    var director:String ? = "",
    var url:String ? = ""
) : Parcelable