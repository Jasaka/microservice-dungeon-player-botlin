package thkoeln.dungeon.botlin.api

import org.json.JSONObject
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*


@Service
class GameAdapter() {

    private val restTemplate = RestTemplate()
    private val gameBaseURL: String = "http://localhost:8080"

    fun createPlayer(name: String, email: String): String? {
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("email", email)
        return postObject(gameBaseURL)
    }

    fun registerPlayer(gameId: UUID, playerId: UUID): String? {
        return putValue("$gameBaseURL/games/$gameId/players/$playerId")
    }

    fun getCurrentTime(gameId: UUID): String? {
        return getJSON("$gameBaseURL/games/$gameId/time")
    }

    fun getAllGames(): String? {
        return getJSON("$gameBaseURL/games")
    }

    fun getGame(gameId: UUID): String? {
        return getJSON("$gameBaseURL/games/$gameId")
    }

    fun getCommandsOfRound(gameId: UUID): String? {
        return getJSON("$gameBaseURL/games/$gameId")
    }

    fun issueCommand(gameId: UUID): String? {
        val jsonObject = JSONObject()
        jsonObject.put("gameId", gameId)
        return postObject("$gameBaseURL/commands")
    }

    fun getJSON(targetUrl: String): String? {
        return restTemplate.getForObject(targetUrl, String::class.java)
    }

    fun postObject(url: String, jsonObject: JSONObject = JSONObject()): String? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request: HttpEntity<String> = HttpEntity(jsonObject.toString(), headers)

        val response = restTemplate.exchange(url, HttpMethod.POST, request, String::class.java)

        return if (response.statusCode == HttpStatus.OK) {
            response.body
        } else {
            null
        }
    }

    fun putValue(url: String, jsonObject: JSONObject = JSONObject()): String? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val request: HttpEntity<String> = HttpEntity(jsonObject.toString(), headers)

        val response = restTemplate.exchange(url, HttpMethod.PUT, request, String::class.java)

        return if (response.statusCode == HttpStatus.OK) {
            response.body
        } else {
            null
        }
    }

}