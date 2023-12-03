package com.example.latihan15_juaraandroid.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Programming(
    val dateProg: Int,
    @StringRes val name: Int,
    @DrawableRes val imgResourceId: Int,
    @StringRes val descripText: Int
)