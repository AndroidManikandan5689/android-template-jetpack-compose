package com.chozhanaadu.androidtemplate.di

import com.chozhanaadu.androidtemplate.data.repository.ItemRepositoryImpl
import com.chozhanaadu.androidtemplate.domain.repository.ItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindItemRepository(
        itemRepositoryImpl: ItemRepositoryImpl
    ): ItemRepository
}