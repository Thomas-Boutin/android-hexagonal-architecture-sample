package fr.sample.hexagonalarchitecture.core_characters.config

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.sample.hexagonalarchitecture.core_characters.adapter.output.http.GetCharactersQueryFactory
import fr.sample.hexagonalarchitecture.core_characters.adapter.output.http.GraphQLCharactersOutputAdapter
import fr.sample.hexagonalarchitecture.core_characters.application.port.CharactersService
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharactersPort
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersModule {
    @Binds
    abstract fun bindsGetCharactersUseCase(charactersService: CharactersService): GetCharactersUseCase

    @Binds
    abstract fun bindsGetCharactersPort(graphQLCharactersOutputAdapter: GraphQLCharactersOutputAdapter): GetCharactersPort

    companion object {
        @Provides
        @Singleton
        fun providesGetCharactersQueryFactory() = GetCharactersQueryFactory()
    }
}