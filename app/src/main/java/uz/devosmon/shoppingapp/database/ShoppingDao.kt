package uz.devosmon.shoppingapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.devosmon.shoppingapp.models.ShoppingItems

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingItems)

    @Delete
    suspend fun delete(item:ShoppingItems)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems():LiveData<List<ShoppingItems>>

}