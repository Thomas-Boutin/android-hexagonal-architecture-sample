package fr.sample.hexagonalarchitecture.core_characters.config

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.sample.hexagonalarchitecture.core_characters.application.port.CharactersService
import fr.sample.hexagonalarchitecture.core_characters.application.port.input.GetCharactersUseCase

@Module
@InstallIn(SingletonComponent::class)
interface CharactersModule {
    @Binds
    fun bindsGetCharactersUseCase(charactersService: CharactersService): GetCharactersUseCase
}