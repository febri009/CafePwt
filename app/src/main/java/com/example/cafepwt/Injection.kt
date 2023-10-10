package com.example.cafepwt

import com.example.cafepwt.data.CafeRepository

object Injection {
    fun provideRepository(): CafeRepository {
        return CafeRepository.getInstance()
    }
}