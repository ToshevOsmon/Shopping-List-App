package uz.devosmon.shoppingapp.mvvm

import uz.devosmon.shoppingapp.database.ShoppingDatabase
import uz.devosmon.shoppingapp.models.ShoppingItems

class ShoppingRepository(private val db:ShoppingDatabase) {

    suspend fun insert(items:ShoppingItems) = db.getShoppingDao().insert(items)

    suspend fun delete(items: ShoppingItems) = db.getShoppingDao().delete(items)

    fun getAllItems() = db.getShoppingDao().getAllShoppingItems()


}