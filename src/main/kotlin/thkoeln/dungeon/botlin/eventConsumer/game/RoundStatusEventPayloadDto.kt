package thkoeln.dungeon.botlin.eventConsumer.game

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import thkoeln.dungeon.botlin.game.domain.RoundStatus
import java.util.*

class RoundStatusEventPayloadDto {

    @JsonProperty("roundId")
    private lateinit var roundId : UUID
    @JsonProperty("roundNumber")
    private var roundNumber  : Int = -1
    @JsonProperty("roundStatus")
    private lateinit var roundStatus: RoundStatus

    fun getRoundId(): UUID = roundId
    fun getRoundNumber(): Int = roundNumber
    fun getRoundStatus(): RoundStatus = roundStatus

    companion object{
        fun fromJsonString(jsonString: String): RoundStatusEventPayloadDto{
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, RoundStatusEventPayloadDto::class.java)

        }
    }

}