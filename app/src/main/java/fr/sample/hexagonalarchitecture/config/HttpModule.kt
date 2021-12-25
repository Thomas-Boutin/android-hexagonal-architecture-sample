package fr.sample.hexagonalarchitecture.config

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.sample.hexagonalarchitecture.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient
            .Builder()
            .serverUrl(BuildConfig.GRAPHQL_API_URL)
            .build()
    }
}