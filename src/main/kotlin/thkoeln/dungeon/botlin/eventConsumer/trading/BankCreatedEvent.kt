package thkoeln.dungeon.botlin.eventConsumer.trading

import com.fasterxml.jackson.core.JsonProcessingException
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import java.util.*


class BankCreatedEvent : AbstractEvent {
    private lateinit var playerId: UUID
    private var money: Int = -1;

    constructor(eventIdString: String, timestampString: String, transactionIdString: String, payloadString: String)
            : super(eventIdString, timestampString, transactionIdString) {
        try {
            var payload = BankCreatedPayloadDto.fromJsonString(payloadString)
            playerId = payload.getPlayerId()
            money = payload.getMoney()

        } catch (conversionFailed: JsonProcessingException) {

        }
    }

    fun getPlayerId(): UUID = playerId
    fun getMoney(): Int = money
    fun isValid() : Boolean = playerId!= null && money!= -1
}