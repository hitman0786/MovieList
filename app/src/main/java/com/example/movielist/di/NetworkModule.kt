package com.example.movielist.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.movielist.R
import com.example.movielist.remote.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */

    companion object {

    }

    @Singleton
    @Provides
    internal fun provideRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.drawable.icon)
            .error(R.drawable.icon)
    }
    @Singleton
    @Provides
    internal fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }
    @Singleton
    @Provides
    internal fun provideAppDrawable(application: Application): Drawable? {
        return ContextCompat.getDrawable(application, R.drawable.icon)
    }

    @Provides
    @Reusable
    internal fun providePostApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        //Create a new Interceptor.
        val headerAuthorizationInterceptor = Interceptor { chain ->
            val request: okhttp3.Request = chain.request()
           /* if(Constants.TOKEN != "token") {
                val headers = request.headers().newBuilder().add("Authorization", "Bearer ${Constants.TOKEN}").build()
                request = request.newBuilder().headers(headers).build()
            }*/


            chain.proceed(request)
        }


        return OkHttpClient()
                .newBuilder()
                .addInterceptor(headerAuthorizationInterceptor)
                .addNetworkInterceptor(logging)
                .connectTimeout(25, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl("https://api.myjson.com/bins/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}