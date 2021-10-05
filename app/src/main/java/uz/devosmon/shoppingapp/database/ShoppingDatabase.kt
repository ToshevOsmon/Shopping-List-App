package uz.devosmon.shoppingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.devosmon.shoppingapp.models.ShoppingItems

@Database(entities = [ShoppingItems::class],version = 1,exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShoppingDao():ShoppingDao

    companion object{

        @Volatile
        private var instance:ShoppingDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance=it
            }
        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "shopping.db")
                .build()


    }

}