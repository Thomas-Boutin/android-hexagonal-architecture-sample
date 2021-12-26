package fr.sample.hexagonalarchitecture.config

import com.apollographql.apollo3.ApolloClient
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.sample.hexagonalarchitecture.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpModule {
    private const val CONTENT_TYPE_JSON = "application/json"

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient
            .Builder()
            .serverUrl(BuildConfig.API_URL_GRAPHQL)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val contentType = CONTENT_TYPE_JSON.toMediaType()
        val converterFactory = Json {
            ignoreUnknownKeys = true
        }.asConverterFactory(contentType)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL_REST)
            .addConverterFactory(converterFactory)
            .build()
    }
}