package com.kawler.effmobile.data.di

import com.kawler.effmobile.domain.remote.MockApiService
import com.kawler.effmobile.domain.remote.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMockInterceptor(): MockInterceptor {
        return MockInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mockInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://mock.api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMockApiService(retrofit: Retrofit): MockApiService {
        return retrofit.create(MockApiService::class.java)
    }
}
