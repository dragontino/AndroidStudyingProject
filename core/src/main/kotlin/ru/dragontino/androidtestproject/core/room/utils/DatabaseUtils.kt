package ru.dragontino.androidtestproject.core.room.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

inline fun <reified Database : RoomDatabase> createDatabase(
    context: Context,
    name: String = Database::class.simpleName ?: Database::class.java.simpleName,
    builder: RoomDatabase.Builder<Database>.() -> Unit = {}
): Database {
    return Room.databaseBuilder(
        context = context,
        klass = Database::class.java,
        name = "$name.db"
    )
        .apply(builder)
        .build()
}