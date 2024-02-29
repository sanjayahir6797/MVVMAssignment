package com.example.assignment

import android.app.Application



class MyApp : Application() {

   /* companion object {
        lateinit var database: MyAppDatabase
    }*/

    override fun onCreate() {
        super.onCreate()
       /* database = Room.databaseBuilder(
            applicationContext,
            MyAppDatabase::class.java,
            "my_database"
        ).build()*/
    }
}