package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import com.apollographql.apollo3.ApolloClient
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapter
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeMain
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeWorker
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharactersPort
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GraphQLCharactersOutputAdapter @Inject constructor(
    override val adapterScopeMain: OutputAdapterScopeMain,
    override val adapterScopeWorker: OutputAdapterScopeWorker,
    private val apolloClient: ApolloClient,
    private val getCharactersQueryFactory: GetCharactersQueryFactory
): OutputAdapter, GetCharactersPort {
    override suspend fun getCharacters() = withContext(adapterScopeWorker.coroutineContext) {
        emptyList<Character>()
    }
}