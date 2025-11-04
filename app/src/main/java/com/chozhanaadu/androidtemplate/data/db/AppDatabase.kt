package com.chozhanaadu.androidtemplate.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chozhanaadu.androidtemplate.data.db.dao.ItemDao
import com.chozhanaadu.androidtemplate.data.db.entity.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}