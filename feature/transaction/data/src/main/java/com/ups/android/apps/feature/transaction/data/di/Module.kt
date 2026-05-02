package com.ups.android.apps.feature.transaction.data.di

import com.ups.android.apps.feature.transaction.data.repository.TransactionRepositoryImpl
import com.ups.android.apps.feature.transaction.domain.TransactionRepository
import com.ups.android.apps.storage.database.di.roomModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val addTransactionDataModule = module {
    includes(roomModule)
    singleOf(constructor = ::TransactionRepositoryImpl) bind TransactionRepository::class
}