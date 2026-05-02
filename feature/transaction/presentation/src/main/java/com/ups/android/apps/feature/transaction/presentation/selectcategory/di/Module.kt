package com.ups.android.apps.feature.transaction.presentation.selectcategory.di

import com.ups.android.apps.feature.transaction.domain.usecase.GetCategoryUseCase
import com.ups.android.apps.feature.transaction.presentation.selectcategory.mvi.CategoryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val categoryPresentationModule = module {

    single {
        GetCategoryUseCase(repository = get())
    }
    viewModel {
        CategoryViewModel(getCategoryUseCase = get())
    }
}