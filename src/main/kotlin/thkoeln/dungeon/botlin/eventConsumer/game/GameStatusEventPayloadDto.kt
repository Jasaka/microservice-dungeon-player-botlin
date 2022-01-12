package thkoeln.dungeon.botlin.eventConsumer.game

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.ToString
import thkoeln.dungeon.botlin.game.domain.GameStatus
import java.util.*

@ToString
class GameStatusEventPayloadDto() {
    @JsonProperty("gameId")
    private var gameId : UUID? = null;
    @JsonProperty("status")
    private var gameStatus : GameStatus? = null

    fun getGameStatus() : GameStatus? = gameStatus
    fun getGameId() : UUID? = gameId


    companion object
    {
        fun fromJsonString(jsonString: String) : GameStatusEventPayloadDto
        {
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString,GameStatusEventPayloadDto::class.java)
        }
    }

    constructor(gameIdString: String,gameStatusString: String) : this() {
        gameStatus = GameStatus.valueOf(gameIdString)
        gameId = UUID.fromString(gameIdString)
    }

}
