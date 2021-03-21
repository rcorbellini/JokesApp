package com.corbellini.jokes.di

import android.content.Context
import com.corbellini.jokes.BuildConfig
import com.corbellini.jokes.features.jokes.data.remote.JokeService
import com.corbellini.jokes.features.jokes.data.repositories.JokeRepositoryImp
import com.corbellini.jokes.features.jokes.domain.repositories.JokeRepository
import com.corbellini.jokes.features.jokes.domain.usecases.GetRandomJokeUseCase
import com.corbellini.jokes.features.jokes.domain.usecases.GetRandomJokeUseCaseImp
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton


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
            }.build()


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
    fun provideListRandomJokesUseCase(jokeRepository: JokeRepository): GetRandomJokeUseCase =
        GetRandomJokeUseCaseImp(jokeRepository = jokeRepository)


}

@Module
@InstallIn(ViewModelComponent::class)
class Module