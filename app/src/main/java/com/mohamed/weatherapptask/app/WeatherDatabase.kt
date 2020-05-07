package com.mohamed.weatherapptask.app

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class WeatherDatabase : RoomDatabase(){

    abstract fun WeatherDao() : WeatherDao

    companion object{

        private var instance : WeatherDatabase? = null

        fun getDatabaseInstance(context : Context) : WeatherDatabase {
            val tempInstance =
                instance
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val mRoom = Room.databaseBuilder(context.applicationContext,
                    WeatherDatabase::class.java,"Weather_Database").build()
                instance = mRoom
                return mRoom
            }
        }

    }
}