package com.example.cafepwt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cafepwt.data.CafeRepository
import com.example.cafepwt.ui.screen.DetailCafeViewModel
import com.example.cafepwt.ui.screen.HomeViewModel

class ViewModelFactory(private val repository: CafeRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailCafeViewModel::class.java) -> {
                DetailCafeViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}