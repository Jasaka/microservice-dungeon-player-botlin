package thkoeln.dungeon.botlin.eventConsumer.player

import com.fasterxml.jackson.core.JsonProcessingException
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import thkoeln.dungeon.botlin.eventConsumer.game.GameStatusEventPayloadDto
import java.util.*
import javax.persistence.Entity

@Entity
class PlayerStatusEvent : AbstractEvent {
    private lateinit var userId: UUID
    private var name: String = ""
    private lateinit var lobbyAction: PlayerStatus

    constructor() : super() {

    }

    constructor(eventIdString: String, timestampString: String, transactionIdString: String, payloadString: String)
            : super(eventIdString, timestampString, transactionIdString) {
        try {
            var payload = PlayerStatusEventPayloadDto.fromJsonString(payloadString)
            userId = payload.getUserId()
            name = payload.getName()
            lobbyAction = payload.getPlayerStatus()

        } catch (conversionFailed: JsonProcessingException) {

        }
    }

    fun getUserId(): UUID = userId
    fun getPlayerStatus(): PlayerStatus = lobbyAction
    fun getName(): String = name
    fun isValid(): Boolean = userId != null && lobbyAction != null && name != null
}
