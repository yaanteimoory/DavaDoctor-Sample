package com.yaan.example

import android.app.Application
import com.yaan.example.network.volly.NetworkUtility

class App :Application() {
    val network : NetworkUtility by lazy {
        NetworkUtility(this)
    }
}