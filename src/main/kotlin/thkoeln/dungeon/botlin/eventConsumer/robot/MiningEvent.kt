package thkoeln.dungeon.botlin.eventConsumer.robot

import com.fasterxml.jackson.core.JsonProcessingException
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import javax.persistence.Entity

class MiningEvent : AbstractEvent {

    private var success = false
    private var message = ""
    private var remainingEnergy = 0
    private var updatedInventory = 0
    private var resource = "coal"
    constructor() : super() {

    }

    constructor(eventIdString: String, timestampString: String, transactionIdString: String, payloadString: String)
            : super(eventIdString, timestampString, transactionIdString) {
        try {
            var payload = MiningEventPayloadDto.fromJsonString(payloadString)
            success = payload.getSuccess()
            message = payload.getMessage()
            remainingEnergy = payload.getRemainingEnergy()
            updatedInventory = payload.getNewInventory()
            resource = payload.getResource()

        } catch (conversionFailed: JsonProcessingException) {

        }
    }

    fun getMessage() = message
}