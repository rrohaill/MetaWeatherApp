package io.rohail.metaweatherapp.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.rohail.metaweatherapp.network.ApiFactory
import io.rohail.metaweatherapp.network.WeatherApi
import io.rohail.metaweatherapp.dashboard.data.WeatherDataSource
import io.rohail.metaweatherapp.dashboard.data.WeatherDataSourceImpl
import io.rohail.metaweatherapp.dashboard.data.WeatherRepository
import io.rohail.metaweatherapp.dashboard.data.WeatherRepositoryImpl
import io.rohail.metaweatherapp.dashboard.usecase.FetchWeatherUseCase
import io.rohail.metaweatherapp.dashboard.usecase.FetchWeatherUseCaseImpl
import io.rohail.metaweatherapp.dashboard.usecase.GetWeatherUseCase
import io.rohail.metaweatherapp.dashboard.usecase.GetWeatherUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(@ApplicationContext appContext: Context): WeatherRepository =
        WeatherRepositoryImpl(
            provideWeatherDataSource(appContext = appContext)
        )

    @Provides
    @Singleton
    fun provideWeatherDataSource(@ApplicationContext appContext: Context): WeatherDataSource =
        WeatherDataSourceImpl(
            service = provideApi(appContext = appContext)
        )

    @Provides
    @Singleton
    fun provideApi(@ApplicationContext appContext: Context): WeatherApi =
        ApiFactory.getApi(context = appContext)

}

//Usecases
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindFetchWeatherUseCase(impl: FetchWeatherUseCaseImpl): FetchWeatherUseCase

    @Binds
    abstract fun bindGetWeatherUseCase(impl: GetWeatherUseCaseImpl): GetWeatherUseCase
}
