package com.example.bmicalculator

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this) // initializing realm
        val realmConfig = RealmConfiguration.Builder().name("result.realm").build()
        Realm.setDefaultConfiguration(realmConfig)

    }
}