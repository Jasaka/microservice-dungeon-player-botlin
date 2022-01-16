package thkoeln.dungeon.botlin.command

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.*
import java.util.*

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

class CommandPayloadDto {
    @JsonProperty("gameId")
    private lateinit var gameId : UUID

    @JsonProperty("playerId")
    private lateinit var playerId: UUID

    @JsonProperty("robotId")
    private lateinit var robotId: UUID

    @JsonProperty("commandType")
    private lateinit var commandType: CommandType

    fun getGameId(): UUID = gameId
    fun getPlayerId(): UUID = playerId
    fun getRobotId(): UUID = robotId
    fun getCommandType(): CommandType = commandType


    companion object{
        fun fromJsonString(jsonString: String): CommandPayloadDto {
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, CommandPayloadDto::class.java)

        }
    }
}