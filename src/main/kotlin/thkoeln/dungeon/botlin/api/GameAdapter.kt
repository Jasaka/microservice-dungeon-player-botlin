package thkoeln.dungeon.botlin.api

import khttp.responses.Response
import java.util.*

class GameAdapter {

    private val gameURL: String = "http://localhost:8081"

    fun createPlayer() {
        val request = khttp.post(gameURL)
        println(request)
    }

    fun registerPlayer(gameID: UUID, playerId: UUID) {
        val request = khttp.put("$gameURL/games/$gameID/players/$playerId")
        println(request)
    }

    fun getCurrentTime(gameID: UUID) {
        val request = khttp.get("$gameURL/games/$gameID/time")
        println(request)
    }

    fun getRunningGames() {
        val request = khttp.get("$gameURL/games")
        println(request)
    }

    fun getGame(gameId: UUID): Response {
        val request = khttp.get("$gameURL/games/$gameId")
        println(request)
        return request
    }

    fun getCommandsOfRound(gameID: UUID) {
        val request = khttp.get("$gameURL/games/$gameID")
        println(request)
    }

    fun issueCommand(gameID: UUID) {
        val request = khttp.post("$gameURL/games/$gameID")
        println(request)
    }

}