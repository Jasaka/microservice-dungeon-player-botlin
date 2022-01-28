package thkoeln.dungeon.botlin.eventConsumer.robot

import com.fasterxml.jackson.core.JsonProcessingException
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import java.util.*
import javax.persistence.*

class MovementEvent : AbstractEvent {

    private var success = false
    private var message = ""
    private var remainingEnergy = 0
    lateinit var planet : Planet

    private var robots : MutableList<String> = mutableListOf()


    constructor() : super() {

    }

    constructor(eventIdString: String, timestampString: String, transactionIdString: String, payloadString: String)
            : super(eventIdString, timestampString, transactionIdString) {
        try {
            var payload = MovementEventPayloadDto.fromJsonString(payloadString)
            success = payload.getSuccess()
            message = payload.getMessage()
            remainingEnergy = payload.getRemainingEnergy()
            planet = payload.getPlanet()
            robots = payload.getRobots()
        } catch (conversionFailed: JsonProcessingException) {

        }
    }
    fun isValid() = success != null && !message.isEmpty() && planet != null && !robots.isEmpty()
}

//TODO MOCK
@Entity
class Planet {
    @Id
    private var id = UUID.randomUUID()

}
