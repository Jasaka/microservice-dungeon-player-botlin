package thkoeln.dungeon.botlin.eventConsumer.map

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonProcessingException
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import java.util.*
import javax.persistence.Entity

@Entity
class NeighbourEvent : AbstractEvent {
    private lateinit var planetId: UUID
    private var movementDifficulty: Int = 0;
    private lateinit var direction: Direction

    fun getPlanetId() = planetId
    fun getMovementDiff() = movementDifficulty
    fun getDirection() = direction
    constructor() : super() {

    }

    constructor(eventIdString: String, timestampString: String, transactionIdString: String, payloadString: String)
            : super(eventIdString, timestampString, transactionIdString) {
        try {
            var payload = NeighbourEventPayloadDto.fromJsonString(payloadString)
            planetId = payload.getPlanetId()
            movementDifficulty = payload.getMovementDiff()
            direction = payload.getDirection()
        } catch (conversionFailed: JsonProcessingException) {

        }
    }

    fun isValid() = planetId != null && movementDifficulty != 0 && direction != null
}