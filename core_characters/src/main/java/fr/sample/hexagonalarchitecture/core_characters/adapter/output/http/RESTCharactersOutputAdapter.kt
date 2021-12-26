package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import fr.sample.hexagonalarchitecture.commons_io.OutputAdapter
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeMain
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeWorker
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharacterDetailPort
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
import javax.inject.Inject

class RESTCharactersOutputAdapter @Inject constructor(
    override val adapterScopeMain: OutputAdapterScopeMain,
    override val adapterScopeWorker: OutputAdapterScopeWorker,
    private val restCharactersApi: RESTCharactersApi,
): OutputAdapter, GetCharacterDetailPort {
    override suspend fun getCharacterDetailWith(characterId: String): CharacterDetail {
        return CharacterDetail(id = "id1", name = "bob", isAlive = true)
    }
}