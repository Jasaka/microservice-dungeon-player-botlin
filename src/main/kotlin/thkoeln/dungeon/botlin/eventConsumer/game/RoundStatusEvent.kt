package thkoeln.dungeon.botlin.eventConsumer.game

import com.fasterxml.jackson.core.JsonProcessingException
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import thkoeln.dungeon.botlin.game.domain.RoundStatus
import java.util.*

class RoundStatusEvent : AbstractEvent {
    private lateinit var roundId: UUID
    private var roundNumber: Int = -1
    private lateinit var roundStatus: RoundStatus

    constructor() : super() {

    }

    constructor(eventIdString: String, timestampString: String, transactionIdString: String, payloadString: String)
            : super(eventIdString, timestampString, transactionIdString) {
        try {
            var payload = RoundStatusEventPayloadDto.fromJsonString(payloadString)
            roundId = payload.getRoundId()
            roundNumber= payload.getRoundNumber()
           roundStatus = payload.getRoundStatus()

        } catch (conversionFailed: JsonProcessingException) {

        }
    }
    fun getRoundId(): UUID = roundId
    fun getRoundNumber(): Int = roundNumber
    fun getRoundStatus(): RoundStatus = roundStatus

    fun isValid() : Boolean = roundId != null && roundNumber != -1 && roundStatus != null
}