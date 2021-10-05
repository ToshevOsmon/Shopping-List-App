package uz.devosmon.shoppingapp.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.devosmon.shoppingapp.models.ShoppingItems

class ShoppingViewModel(private val repository: ShoppingRepository) :ViewModel() {

    fun insert(items:ShoppingItems) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items: ShoppingItems) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllItems() = repository.getAllItems()




}