package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import fr.sample.hexagonalarchitecture.commons_io.OutputAdapter
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeMain
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeWorker
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharactersPort
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GraphQLCharactersOutputAdapter @Inject constructor(
    override val adapterScopeMain: OutputAdapterScopeMain,
    override val adapterScopeWorker: OutputAdapterScopeWorker
): OutputAdapter, GetCharactersPort {
    override suspend fun getCharacters() = withContext(adapterScopeWorker.coroutineContext) {
        TODO() //GetCharactersQuery().
    }
}