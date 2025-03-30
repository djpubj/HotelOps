package com.test.hotelops

import android.app.Application
import com.test.hotelops.roomdatabase.repository.Graph

class Databasecreate : Application() {

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}