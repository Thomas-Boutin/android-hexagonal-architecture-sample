package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import fr.sample.hexagonalarchitecture.commons_io.OutputAdapter
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeMain
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeWorker
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharacterDetailPort
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RESTCharactersOutputAdapter @Inject constructor(
    override val adapterScopeMain: OutputAdapterScopeMain,
    override val adapterScopeWorker: OutputAdapterScopeWorker,
    private val restCharactersApi: RESTCharactersApi,
) : OutputAdapter, GetCharacterDetailPort {
    override suspend fun getCharacterDetailWith(characterId: String) =
        withContext(adapterScopeWorker.coroutineContext) {
            restCharactersApi
                .getCharacterWith(characterId)
                .toCharacterDetail()
        }
}