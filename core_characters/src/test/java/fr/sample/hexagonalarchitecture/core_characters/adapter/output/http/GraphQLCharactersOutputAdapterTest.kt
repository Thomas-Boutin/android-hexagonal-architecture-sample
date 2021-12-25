package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeMain
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeWorker
import fr.sample.hexagonalarchitecture.core_characters.domain.Character
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class GraphQLCharactersOutputAdapterTest {
    private lateinit var apolloClient: ApolloClient
    private lateinit var getCharactersQueryFactory: GetCharactersQueryFactory
    private lateinit var graphQLCharactersOutputAdapter: GraphQLCharactersOutputAdapter

    @Before
    fun setUp() {
        val scheduler = StandardTestDispatcher()
        Dispatchers.setMain(scheduler)
        apolloClient = mockk(relaxed = true)
        getCharactersQueryFactory = mockk(relaxed = true)
        graphQLCharactersOutputAdapter = GraphQLCharactersOutputAdapter(
            OutputAdapterScopeMain(scheduler),
            OutputAdapterScopeWorker(scheduler),
            apolloClient,
            getCharactersQueryFactory
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should fetch new characters`() = runTest {
        val getCharactersQuery = GetCharactersQuery()
        val apolloCall = mockk<ApolloCall<GetCharactersQuery.Data>>(relaxed = true)
        coEvery { getCharactersQueryFactory.create() } returns getCharactersQuery
        coEvery { apolloClient.query(getCharactersQuery) } returns apolloCall
        coEvery { apolloCall.execute() } returns ApolloResponse.Builder<GetCharactersQuery.Data>(
            mockk(),
            mockk(),
            GetCharactersQuery.Data(
                characters = GetCharactersQuery.Characters(
                    listOf(
                        GetCharactersQuery.Result(id = "id1", name = "bob"),
                        GetCharactersQuery.Result(id = "id2", name = "pamela"),
                        GetCharactersQuery.Result(id = "id3", name = "gaga"),
                    )
                )
            )
        ).build()

        assertThat(graphQLCharactersOutputAdapter.getCharacters()).containsExactly(
            Character(id = "id1", name = "bob"),
            Character(id = "id2", name = "pamela"),
            Character(id = "id3", name = "gaga")
        )
    }

    /*@Test
    fun `should propagate error`() = runTest {
        coEvery { getCharactersUseCase.getCharacters() } returns Result.failure(
            Exception("Unable to fetch characters")
        )

        assertThat(charactersInputAdapter.getCharacters().exceptionOrNull())
            .isExactlyInstanceOf(Exception::class.java)
            .hasMessage("Unable to fetch characters")
    }*/
}