package com.example.testingappdemo.di

import android.content.Context
import androidx.room.Room
import com.example.testingappdemo.data.local.ShoppingDao
import com.example.testingappdemo.data.local.ShoppingItemDatabase
import com.example.testingappdemo.data.remote.PixabayAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideShoppingItemDatabase(@ApplicationContext context: Context): ShoppingItemDatabase =
        Room.databaseBuilder(context, ShoppingItemDatabase::class.java, "shopping_item").build()

    @Singleton
    @Provides
    fun provideShoppingDao(shoppingItemDatabase: ShoppingItemDatabase): ShoppingDao =
        shoppingItemDatabase.shoppingDao()


    @Singleton
    @Provides
    fun provideBaseURL(): String = "https://pixabay.com/"

    @Singleton
    @Provides
    fun provideRetrofit(baseURL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providePixabayApi(retrofit: Retrofit): PixabayAPI =
        retrofit.create(PixabayAPI::class.java)
}