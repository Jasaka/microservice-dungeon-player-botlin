package thkoeln.dungeon.botlin.eventConsumer.trading

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import thkoeln.dungeon.botlin.eventConsumer.game.RoundStatusEventPayloadDto
import java.util.*

class TradingEventPayloadDto {
    @JsonProperty("playerId")
    private lateinit var playerId: UUID

    @JsonProperty("money")
    private var money: Int = 0;

    fun getPlayerId(): UUID = playerId
    fun getMoney(): Int = money

    companion object{
        fun fromJsonString(jsonString: String): TradingEventPayloadDto{
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, TradingEventPayloadDto::class.java)

        }
    }
}
