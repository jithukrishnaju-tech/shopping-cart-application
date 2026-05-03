package com.retailcloudandroid.retailcloudmachinetestandroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.retailcloudandroid.retailcloudmachinetestandroid.data.local.dao.CartDao
import com.retailcloudandroid.retailcloudmachinetestandroid.data.local.entity.CartItemEntity

@Database(entities = [CartItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
