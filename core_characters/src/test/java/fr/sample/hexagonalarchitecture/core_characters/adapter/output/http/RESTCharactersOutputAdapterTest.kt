package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeMain
import fr.sample.hexagonalarchitecture.commons_io.OutputAdapterScopeWorker
import fr.sample.hexagonalarchitecture.core_characters.domain.CharacterDetail
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

class RESTCharactersOutputAdapterTest {
    private lateinit var restCharactersApi: RESTCharactersApi
    private lateinit var restCharactersOutputAdapter: RESTCharactersOutputAdapter

    @Before
    fun setUp() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
        restCharactersApi = mockk(relaxed = true)
        restCharactersOutputAdapter = RESTCharactersOutputAdapter(
            OutputAdapterScopeMain(dispatcher),
            OutputAdapterScopeWorker(dispatcher),
            restCharactersApi,
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should fetch new characters`() = runTest {
        coEvery { restCharactersApi.getCharacterWith(any()) } returns RESTCharacter(
            id = "id1",
            name = "bob",
            isAlive = true,
        )

        assertThat(restCharactersOutputAdapter.getCharacterDetailWith("id1")).isEqualTo(
            CharacterDetail(id = "id1", name = "bob", isAlive = true)
        )
    }
/*
    @Test
    fun `should propagate error`() = runTest {
        val getCharactersQuery = GetCharactersQuery()
        val apolloCall = mockk<ApolloCall<GetCharactersQuery.Data>>(relaxed = true)
        coEvery { getCharactersQueryFactory.create() } returns getCharactersQuery
        coEvery { apolloClient.query(getCharactersQuery) } returns apolloCall
        coEvery { apolloCall.execute() } returns ApolloResponse.Builder(
            mockk(),
            mockk(),
            GetCharactersQuery.Data(
                characters = null,
            )
        ).errors(
            listOf(
                Error(
                    message = "500",
                    locations = null,
                    extensions = null,
                    path = null,
                    nonStandardFields = null
                )
            )
        ).build()

        val result = wrapInResource { graphQLCharactersOutputAdapter.getCharacters() }
        Assertions.assertThat(result.exceptionOrNull())
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("[500]")
    }*/
}