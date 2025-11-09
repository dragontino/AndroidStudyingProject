package ru.dragontino.androidtestproject.core.room

import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dragontino.androidtestproject.core.room.dao.BouquetsDao
import ru.dragontino.androidtestproject.core.room.dao.FlowersDao
import ru.dragontino.androidtestproject.core.room.entity.BouquetEntity
import ru.dragontino.androidtestproject.core.room.entity.FlowerEntity
import ru.dragontino.androidtestproject.core.room.entity.FlowerSet
import ru.dragontino.androidtestproject.core.room.utils.createDatabase

@Database(
    entities = [FlowerEntity::class, BouquetEntity::class, FlowerSet::class],
    version = 1,
    exportSchema = true
)
abstract class FlowersDatabase : RoomDatabase() {
    abstract val flowersDao: FlowersDao

    abstract val bouquetsDao: BouquetsDao


    companion object {
        @Volatile
        private var INSTANCE: FlowersDatabase? = null

        fun getInstance(
            context: Context
        ): FlowersDatabase {
            val instance = INSTANCE ?:
            synchronized<FlowersDatabase>(this) {
                createDatabase(context) {
                    addCallback(SeedDatabaseCallback())
                }
            }.also {
                INSTANCE = it
            }
            return instance
        }
    }


    private class SeedDatabaseCallback : Callback() {
        private val lifecycleOwner = ProcessLifecycleOwner.get()
        
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            lifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val database = INSTANCE ?: return@launch

                val flowers = listOf(
                    FlowerEntity(id = 0, name = "Красная роза", quantity = 100),
                    FlowerEntity(id = 0, name = "Белая роза", quantity = 100),
                    FlowerEntity(id = 0, name = "Тюльпан", quantity = 100),
                    FlowerEntity(id = 0, name = "Гвоздика", quantity = 100),
                    FlowerEntity(id = 0, name = "Пион", quantity = 80),
                    FlowerEntity(id = 0, name = "Орхидея", quantity = 60),
                    FlowerEntity(id = 0, name = "Хризантема", quantity = 90),
                    FlowerEntity(id = 0, name = "Ирис", quantity = 70),
                    FlowerEntity(id = 0, name = "Гербера", quantity = 90),
                    FlowerEntity(id = 0, name = "Лилия", quantity = 100)
                )
                database.flowersDao.addFlowers(flowers)
            }
        }
    }
}