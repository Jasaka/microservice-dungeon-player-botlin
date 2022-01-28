package thkoeln.dungeon.botlin.eventConsumer.robot

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonProcessingException
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import java.util.*
import javax.persistence.Entity

class SpawnNotificationEvent : AbstractEvent{


    private lateinit var robotId : UUID
    private lateinit var playerId: UUID
    private lateinit var robotsInSight: MutableList<UUID>

    constructor(eventIdString: String, timestampString: String, transactionIdString: String, payloadString: String)
            : super(eventIdString, timestampString, transactionIdString) {
        try {
            var payload = SpawnNotificationEventPayloadDto.fromJsonString(payloadString)
            robotId = payload.getRobotId()
            playerId = payload.getPlayerId()
            robotsInSight = payload.getRobotsInSight()

        } catch (conversionFailed: JsonProcessingException) {

        }
    }
}