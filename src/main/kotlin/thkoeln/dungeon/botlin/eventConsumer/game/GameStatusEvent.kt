package thkoeln.dungeon.botlin.eventConsumer.game

import com.fasterxml.jackson.core.JsonProcessingException
import org.springframework.messaging.MessageHeaders
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import thkoeln.dungeon.botlin.game.domain.GameStatus
import java.util.*
import javax.persistence.Entity

@Entity
class GameStatusEvent : AbstractEvent {
    private lateinit var gameStatus: GameStatus
    private lateinit var gameID: UUID

    constructor() : super() {

    }

    constructor(eventIdString: String, timestampString: String, transactionIdString: String, payloadString: String)
            : super(eventIdString,timestampString,transactionIdString) {
        try {
            var payload = GameStatusEventPayloadDto.fromJsonString(payloadString)
            gameStatus = payload.getGameStatus()!!
            gameID = payload.getGameId()!!

        }
        catch (conversionFailed : JsonProcessingException)
        {

        }
    }


    private companion object {
        val TYPE_KEY = "type";
        val GAME_ID_KEY = "gameId";
    }

    fun getGameId(): UUID? = gameID;
    fun getGameStatus(): GameStatus? = gameStatus;
    fun isValid() : Boolean = gameID != null && gameStatus != null

}