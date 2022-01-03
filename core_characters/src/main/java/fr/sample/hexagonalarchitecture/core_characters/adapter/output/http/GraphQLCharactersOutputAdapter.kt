package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapter
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeMain
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeWorker
import fr.sample.hexagonalarchitecture.core_characters.application.port.output.GetCharactersPort
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterId
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterName
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GraphQLCharactersOutputAdapter @Inject constructor(
    override val adapterScopeMain: OutputAdapterScopeMain,
    override val adapterScopeWorker: OutputAdapterScopeWorker,
    private val apolloClient: ApolloClient,
    private val getCharactersQueryFactory: GetCharactersQueryFactory
): OutputAdapter, GetCharactersPort {
    override suspend fun getCharacters() = withContext(adapterScopeWorker.coroutineContext) {
        apolloClient
            .query(getCharactersQueryFactory.create())
            .execute()
            .getDataOrThrow()
            .characters
            ?.results
            ?.filterNotNull()
            ?.map(GetCharactersQuery.Result::mapToCharacter)
            ?: emptyList()
    }
}

private fun <D: Operation.Data> ApolloResponse<D>.getDataOrThrow(): D {
    if (hasErrors()) throw RuntimeException(errors.orEmpty().map { it.message }.toString())
    requireNotNull(data)

    return data as D
}

private fun GetCharactersQuery.Result.mapToCharacter(): Character {
    return Character(id = CharacterId(id.orEmpty()), name = CharacterName(name.orEmpty()))
}
