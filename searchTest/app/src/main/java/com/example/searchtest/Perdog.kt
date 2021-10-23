package com.example.searchtest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Perdog(
    var dogname: String,
    var doglocation: String,
    var avatar: Int,
    var doggender: String,
    var dogdetail: String
) : Parcelable
