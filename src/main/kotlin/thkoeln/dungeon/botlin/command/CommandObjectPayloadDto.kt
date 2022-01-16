package thkoeln.dungeon.botlin.command

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.*
import thkoeln.dungeon.botlin.eventConsumer.game.RoundStatusEventPayloadDto
import thkoeln.dungeon.botlin.eventConsumer.player.PlayerStatus
import thkoeln.dungeon.botlin.game.domain.RoundStatus
import java.util.*

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

/*
"commandType": "blocking",
"planetId": "d290f1ee-6c54-4b01-90e6-d701748f0851",
"targetId": "d290f1ee-6c54-4b01-90e6-d701748f0851",
"itemName": "string",
"itemQuantity": 5
*/

class CommandObjectPayloadDto() {
    @JsonProperty("commandType")
    private lateinit var commandType: CommandType

    @JsonProperty("planetId")
    private lateinit var planetId : UUID

    @JsonProperty("targetId")
    private lateinit var targetId  : UUID

    @JsonProperty("itemName")
    private lateinit var itemName: String

    @JsonProperty("itemQuantity")
    private var itemQuantity: Int = -1

    fun getCommandType(): CommandType? = commandType
    fun getPlanetId(): UUID = planetId
    fun getTargetId(): UUID = targetId
    fun getItemName(): String = itemName
    fun getItemQuantity(): Int = itemQuantity

    companion object{
        fun fromJsonString(jsonString: String): CommandObjectPayloadDto {
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, CommandObjectPayloadDto::class.java)

        }
    }

}