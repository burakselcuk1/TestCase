package com.example.testcase.di

import com.example.testcase.domain.ProductService
import com.example.testcase.presentation.productMainFragment.model.ProductUiMapper
import com.example.testcase.repository.ProductRepository
import com.example.testcase.repository.repositoryImpl.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun productUiMapper(): ProductUiMapper {
        return ProductUiMapper()
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        productService: ProductService,
        productUiMapper: ProductUiMapper
    ): ProductRepository {
        return ProductRepositoryImpl(productService, productUiMapper)
    }
}