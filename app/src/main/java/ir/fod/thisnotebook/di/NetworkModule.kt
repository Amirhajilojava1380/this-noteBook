package ir.fod.thisnotebook.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.fod.thisnotebook.data.api.NetWorkApi
import ir.fod.thisnotebook.utils.Constant.Companion.URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesGsonConverter():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providesCache(@ApplicationContext context :Context):Cache{
        return Cache(context.cacheDir , 10 * 10 * 1024)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(cache: Cache):OkHttpClient{

        return OkHttpClient.Builder().
        connectTimeout(15 , TimeUnit.SECONDS).
        readTimeout(15 , TimeUnit.SECONDS).
        cache(cache)
        .build()

    }




    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient , gsonConverterFactory: GsonConverterFactory):Retrofit{

        return Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    }




    @Singleton
    @Provides
    fun providesCreate(retrofit: Retrofit) : NetWorkApi{

        return retrofit.create(NetWorkApi::class.java)

    }






}