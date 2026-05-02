package com.ups.android.apps.storage.database.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ups.android.apps.storage.database.TeamupDatabase
import org.koin.dsl.module

val roomModule = module {

    single {
        Room.databaseBuilder(
            get(),
            TeamupDatabase::class.java,
            "finance_database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                TeamupDatabase.prepopulateDatabase(get())
            }
        }).build()
    }

    single { get<TeamupDatabase>().transactionDao() }
}