package thkoeln.dungeon.botlin.eventConsumer.trading

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class BankCreatedPayloadDto {
    @JsonProperty("playerId")
    private lateinit var playerId: UUID

    @JsonProperty("money")
    private var money: Int = 0;

    fun getPlayerId(): UUID = playerId
    fun getMoney(): Int = money

    companion object{
        fun fromJsonString(jsonString: String): BankCreatedPayloadDto{
            var objectMapper = ObjectMapper().findAndRegisterModules()
            return objectMapper.readValue(jsonString, BankCreatedPayloadDto::class.java)

        }
    }
}
