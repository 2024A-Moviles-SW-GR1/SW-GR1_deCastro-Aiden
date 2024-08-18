package com.example.subscriptionserviceexam

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Subscription::class, Plan::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun subscriptionDao(): SubscriptionDao
    abstract fun planDao(): PlanDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "subscription_service_db"
                ).build()
            }
            return instance!!
        }
    }
}