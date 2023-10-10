package com.example.cafepwt.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class CafeRepository {

    private val dataCafe = mutableListOf<CafeList>()

    init {
        if (dataCafe.isEmpty()){
            CafeData.cafe.forEach {
                dataCafe.add(CafeList(it, 0))
            }
        }
    }

    fun getAllCafe(): Flow<List<CafeList>> {
        return flowOf(dataCafe)
    }

    fun getCafeById(cafeId: Long): CafeList{
        return dataCafe.first {
            it.cafe.id == cafeId
        }
    }

    fun searchCafe(query: String): Flow<List<CafeList>> {
        return flow {
            val filteredCafes = dataCafe.filter { cafe ->
                cafe.cafe.name.contains(query, ignoreCase = true)
            }
            emit(filteredCafes)
        }
    }

    companion object {
        @Volatile
        private var instance: CafeRepository? = null

        fun getInstance(): CafeRepository =
            instance ?: synchronized(this) {
                CafeRepository().apply {
                    instance = this
                }
            }
    }
}