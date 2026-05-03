package com.retailcloudandroid.retailcloudmachinetestandroid.di

import com.retailcloudandroid.retailcloudmachinetestandroid.data.repository.ShopRepositoryImpl
import com.retailcloudandroid.retailcloudmachinetestandroid.domain.repository.ShopRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindShopRepository(
        shopRepositoryImpl: ShopRepositoryImpl
    ): ShopRepository
}
