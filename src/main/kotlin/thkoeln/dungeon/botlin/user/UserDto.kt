package thkoeln.dungeon.botlin.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import thkoeln.dungeon.botlin.eventConsumer.game.RoundStatusEventPayloadDto
import java.util.*

class UserDto {
    @JsonProperty("playerId")
    var id = UUID.randomUUID()

    @JsonProperty("name")
    lateinit var name: String
    @JsonProperty("email")
    lateinit var email: String
    @JsonProperty("bearerToken")
    lateinit var bearerToken: UUID


    companion object{
        fun fromJsonString(jsonString: String): UserDto{
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, UserDto::class.java)

        }
    }
}