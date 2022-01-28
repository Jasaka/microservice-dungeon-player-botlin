package thkoeln.dungeon.botlin.eventConsumer.robot

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonProcessingException
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import java.util.*

class FinishedMiningEvent : AbstractEvent {

    private lateinit var planetId: UUID
    private lateinit var resourceId: UUID
    private var resourceType: String = ""
    private var amountMined = -1
    private var amountLeft = -1

    constructor()

    constructor(eventIdString: String, timestampString: String, transactionIdString: String, payloadString: String)
            : super(eventIdString, timestampString, transactionIdString) {
        try {
            var payload = FinishedMiningEventPayloadDto.fromJsonString(payloadString)
            planetId = payload.getPlanetId()
            resourceId = payload.getResourceId()
            resourceType = payload.getResourceType()
            amountMined = payload.getAmountMined()
            amountLeft = payload.getAmountLeft()

        } catch (conversionFailed: JsonProcessingException) {

        }
    }
}