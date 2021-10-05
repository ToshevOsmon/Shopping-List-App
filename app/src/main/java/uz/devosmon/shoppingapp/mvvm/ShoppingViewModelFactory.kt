package uz.devosmon.shoppingapp.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoppingViewModelFactory(private val repository: ShoppingRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T: ViewModel?> create(modelClass: Class<T>):T{
        return ShoppingViewModel(repository) as T
    }


}