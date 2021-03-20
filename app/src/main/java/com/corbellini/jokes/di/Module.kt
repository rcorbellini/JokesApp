package com.corbellini.jokes.di

import android.content.Context
import com.corbellini.jokes.BuildConfig
import com.corbellini.jokes.features.jokes.data.remote.JokeService
import com.corbellini.jokes.features.jokes.data.repositories.JokeRepositoryImp
import com.corbellini.jokes.features.jokes.domain.repositories.JokeRepository
import com.corbellini.jokes.features.jokes.domain.usecases.ListRandomJokesUseCase
import com.corbellini.jokes.features.jokes.domain.usecases.ListRandomJokesUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton
import com.google.gson.GsonBuilder


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (!BuildConfig.DEBUG) return@apply
                addInterceptor(HttpLoggingInterceptor { Timber.tag("OkHttp :::\t").d(it) }
                    .apply { level = HttpLoggingInterceptor.Level.BODY }
                )
            }
            .cache(Cache(context.cacheDir, 5L * 1024 * 1024))
            .addInterceptor { forceCache(it) }
            .build()

    private fun forceCache(it: Interceptor.Chain, day: Int = 7): Response {
        val request = it.request().newBuilder().header(
            "Cache-Control",
            "max-stale=" + 60 * 60 * 24 * day
        ).build()
        val response = it.proceed(request)
        Timber.d("provideOkHttpClient: response: $response")
        Timber.i("provideOkHttpClient: cacheControl: ${response.cacheControl}")
        Timber.i("provideOkHttpClient: networkResponse: ${response.networkResponse}")
        return response
    }


    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    fun provideJokeService(retrofit: Retrofit.Builder): JokeService =
        retrofit.baseUrl(JokeService.BASE_URL).build().create(JokeService::class.java)

    @Provides
    fun provideJokeRepository(service: JokeService): JokeRepository =
        JokeRepositoryImp(jokeService = service)

    @Provides
    fun provideListRandomJokesUseCase(jokeRepository: JokeRepository): ListRandomJokesUseCase =
        ListRandomJokesUseCaseImp(jokeRepository = jokeRepository)


}

@Module
@InstallIn(ViewModelComponent::class)
class Module