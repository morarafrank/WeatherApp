package com.morarafrank.weatherapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.morarafrank.weatherapp.data.local.WeatherDatabase
import com.morarafrank.weatherapp.data.local.forecast.ForecastDao
import com.morarafrank.weatherapp.data.local.weather.WeatherDao
import com.morarafrank.weatherapp.data.remote.WeatherService
import com.morarafrank.weatherapp.utils.Constants
import com.morarafrank.weatherapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Shared Prefs
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            Constants.Preferences.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    // Room Database
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): androidx.room.RoomDatabase {
//        return Room.databaseBuilder(
//                context,
//                WeatherDatabase::class.java,
//                Constants.DATABASE_NAME
//            ).fallbackToDestructiveMigration(false).build()
//    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration(false).build()
    }


    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    // Daos
    @Provides
    @Singleton
    fun provideWeatherDao(appDatabase: WeatherDatabase): WeatherDao {
        return appDatabase.weatherDao()
    }

    @Provides
    @Singleton
    fun provideForecastDao(appDatabase: WeatherDatabase): ForecastDao {
        return appDatabase.forecastDao()
    }

    //Repos

    // Viewmodel

    // Base url
    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String =  Constants.BASE_URL


    // HttpClient
    @Singleton
    @Provides
    fun provideHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(180, TimeUnit.SECONDS)
            .connectTimeout(180, TimeUnit.SECONDS)
            .apply {
                interceptors().addAll(interceptors)
            }
            .build()
    }

    // Interceptors
    @Provides
    fun provideInterceptors(): Set<Interceptor> {
        return setOf(
            Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(request)
            }
        )
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }


//    @Provides
//    @Singleton
//    fun provideKotlinxSerializationConverterFactory(json: Json): Converter.Factory {
//        val contentType = "application/json".toMediaType()
//        return json.asConverterFactory(contentType)
//    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

}