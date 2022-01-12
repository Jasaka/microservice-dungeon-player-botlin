package thkoeln.dungeon.botlin.eventConsumer.player

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.ToString
import java.util.*

@ToString
class PlayerStatusEventPayloadDto() {
    @JsonProperty("userId")
    private lateinit var userId: UUID

    @JsonProperty("lobbyAction")
    private lateinit var lobbyAction: PlayerStatus

    @JsonProperty("name")
    private var name: String = ""

    fun getUserId(): UUID = userId
    fun getPlayerStatus(): PlayerStatus = lobbyAction
    fun getName(): String = name

    companion object {
        fun fromJsonString(jsonString: String): PlayerStatusEventPayloadDto {
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, PlayerStatusEventPayloadDto::class.java)

        }
    }

    constructor(userIdString: String, playerStatusString: String, name: String) : this() {
        lobbyAction = PlayerStatus.valueOf(playerStatusString)
        userId = UUID.fromString(userIdString)
        this.name = name;
    }
}