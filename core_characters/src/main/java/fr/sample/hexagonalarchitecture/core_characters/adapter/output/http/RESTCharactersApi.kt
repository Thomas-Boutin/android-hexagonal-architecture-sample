package fr.sample.hexagonalarchitecture.core_characters.adapter.output.http

import retrofit2.http.GET
import retrofit2.http.Path


interface RESTCharactersApi {
    @GET("character/{characterId}")
    suspend fun getCharacterWith(@Path("characterId") characterId: String): RESTCharacter
}