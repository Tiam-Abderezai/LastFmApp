package com.example.lastfmapp

import android.content.Context
import androidx.room.Room
import com.example.lastfmapp.data.RetrofitRepository
import com.example.lastfmapp.data.RetrofitRepositoryImpl
import com.example.lastfmapp.data.local.AlbumDao
import com.example.lastfmapp.data.local.RoomDB
import com.example.lastfmapp.data.local.RoomRepository
import com.example.lastfmapp.data.local.RoomRepositoryImpl
import com.example.lastfmapp.data.remote.RetrofitService
import com.example.lastfmapp.util.Constants
import com.example.lastfmapp.util.Constants.Companion.BASE_URL
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
    fun provideRoomDB(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, RoomDB::class.java, Constants.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideRetrofitRepository(
        retrofitService: RetrofitService
    ) = RetrofitRepositoryImpl(retrofitService) as RetrofitRepository

    @Singleton
    @Provides
    fun provideAlbumDao(
        database: RoomDB
    ) = database.albumDao()

    @Singleton
    @Provides
    fun provideRoomRepository(
        albumDao: AlbumDao
    ) = RoomRepositoryImpl(albumDao) as RoomRepository

    @Singleton
    @Provides
    fun provideRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitService::class.java)
    }
}
