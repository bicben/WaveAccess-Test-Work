package com.zaripov.waveaccesstestwork.di.modules

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.zaripov.waveaccesstestwork.api.ApiService
import com.zaripov.waveaccesstestwork.api.WaveAccessApi
import com.zaripov.waveaccesstestwork.general.Repository
import com.zaripov.waveaccesstestwork.model.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Module
class ApiServiceModule(private val baseUrl: String = Repository.BASE_URL) {

    @Provides
    @Singleton
    fun provideApiService(waveAccessApi: WaveAccessApi): ApiService {
        return ApiService(waveAccessApi)
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): WaveAccessApi {
        return retrofit.create(WaveAccessApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit {
        return builder
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttp3(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val logger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("OkHttp3Logger", message)
            }
        }) //I have no idea why I can't use Kotlin's functions here to declare an anonymous class

        logger.level = HttpLoggingInterceptor.Level.BASIC
        builder.addInterceptor(logger)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
        //.addConverterFactory(ScalarsConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideGson(deserialiser: JsonDeserializer<Model>): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .setLenient()
            .setDateFormat("YYYY-MM-DD'T'hh:mm:ss z")
            .registerTypeAdapter(Model::class.java, deserialiser)
            .create()
    }

    @Provides
    @Singleton
    fun provideDeserialiser(): JsonDeserializer<Model> {
        return JsonDeserializer { json, _, _ ->
            Log.i("RetrofitModule", "Beginning deserialization...")
            val jsonObject = json.asJsonObject

            val tags = mutableListOf<Tag>()
            val friends = mutableListOf<Friend>()

            val about = jsonObject.getAsJsonPrimitive("about").asString
            val address = jsonObject.getAsJsonPrimitive("address").asString
            val age = jsonObject.getAsJsonPrimitive("age").asInt
            val balance = jsonObject.getAsJsonPrimitive("balance").asString
            val company = jsonObject.getAsJsonPrimitive("company").asString
            val email = jsonObject.getAsJsonPrimitive("email").asString
            val gender = jsonObject.getAsJsonPrimitive("gender").asString
            val guid = jsonObject.getAsJsonPrimitive("guid").asString
            val id = jsonObject.getAsJsonPrimitive("id").asLong
            val isActive = jsonObject.getAsJsonPrimitive("isActive").asBoolean
            val latitude = jsonObject.getAsJsonPrimitive("latitude").asDouble
            val longitude = jsonObject.getAsJsonPrimitive("longitude").asDouble
            val name = jsonObject.getAsJsonPrimitive("name").asString
            val phone = jsonObject.getAsJsonPrimitive("phone").asString

            val favoriteFruitString = jsonObject.getAsJsonPrimitive("favoriteFruit").asString
            val favoriteFruit = Fruit.valueOf(favoriteFruitString.toUpperCase(Locale.ENGLISH))

            val eyeColorString = jsonObject.getAsJsonPrimitive("eyeColor").asString
            val eyeColor = EyeColor.valueOf(eyeColorString.toUpperCase(Locale.ENGLISH))

            val registeredString = jsonObject.getAsJsonPrimitive("registered").asString
            val registered =
                SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss XXX", Locale.ENGLISH).parse(registeredString)
                    .time

            val user = User(
                id,
                about,
                address,
                age,
                balance,
                company,
                email,
                eyeColor,
                favoriteFruit,
                gender,
                guid,
                isActive,
                latitude,
                longitude,
                name,
                phone,
                registered
            )

            val tagsArray = jsonObject.getAsJsonArray("tags")
            tagsArray.forEach { tags.add(Tag(id, it.asString)) }

            val friendsArray = jsonObject.getAsJsonArray("friends")
            friendsArray.forEach {
                friends.add(
                    Friend(
                        id,
                        it.asJsonObject.getAsJsonPrimitive("id").asLong
                    )
                )
            }

            val model = Model()
            model.let {
                it.user = user
                it.friends = friends
                it.tags = tags
            }

            Log.i("RetrofitModule", "Deserialization of $name complete")
            return@JsonDeserializer model
        }
    }
}