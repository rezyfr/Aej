package com.rezyfr.aej.di

import com.rezyfr.aej.BuildConfig
import com.rezyfr.aej.core.network.adapter.NetworkResponseAdapterFactory
import com.rezyfr.aej.data.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideOkHttpClient(headerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(headerInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            val newRequest = request.newBuilder()
                .url(newUrl)
                .method(request.method, request.body)
                .build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    fun provideMovieService(client: OkHttpClient): MovieService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
        return retrofit.create(MovieService::class.java)
    }
}