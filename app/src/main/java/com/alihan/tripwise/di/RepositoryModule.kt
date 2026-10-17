package com.alihan.tripwise.di

import com.alihan.tripwise.data.repository.AuthRepositoryImpl
import com.alihan.tripwise.data.repository.DestinationRepositoryImpl
import com.alihan.tripwise.domain.repository.AuthRepository
import com.alihan.tripwise.domain.repository.DestinationRepository
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
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindDestinationRepository(
        destinationRepositoryImpl: DestinationRepositoryImpl
    ): DestinationRepository
}
