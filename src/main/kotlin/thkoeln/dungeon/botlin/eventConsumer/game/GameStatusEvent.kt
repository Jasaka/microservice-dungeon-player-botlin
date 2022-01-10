package thkoeln.dungeon.botlin.eventConsumer.game

import org.springframework.messaging.MessageHeaders
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import thkoeln.dungeon.botlin.game.domain.GameStatus
import java.util.*
import javax.persistence.Entity

@Entity
class GameStatusEvent : AbstractEvent {
    private lateinit var gameStatus: GameStatus
    private lateinit var gameID: UUID

    constructor() {

    }

    constructor(messageHeaders: MessageHeaders, gameStatusEventPayload: GameStatusEventPayload) : super(messageHeaders){

        gameStatus = gameStatusEventPayload.gameStatus
        gameID = gameStatusEventPayload.gameId
    }


    private companion object {
        val TYPE_KEY = "type";
        val GAME_ID_KEY = "gameId";
    }

    fun getGameId(): UUID? = gameID;
    fun getGameStatus(): GameStatus? = gameStatus;

}