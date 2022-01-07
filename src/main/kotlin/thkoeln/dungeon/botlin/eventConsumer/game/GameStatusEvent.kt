package thkoeln.dungeon.botlin.eventConsumer.game

import org.springframework.messaging.MessageHeaders
import thkoeln.dungeon.botlin.eventConsumer.core.AbstractEvent
import thkoeln.dungeon.botlin.game.domain.GameStatus
import java.util.*
import javax.persistence.Entity

@Entity
class GameStatusEvent(messageHeaders: MessageHeaders, gameStatusEventPayload: GameStatusEventPayload) : AbstractEvent() {
    private val gameStatus: GameStatus? = null
    private var gameID : UUID? = null;

    private companion object {
        val TYPE_KEY = "type";
        val GAME_ID_KEY = "gameId";
    }


    init {
        AbstractEvent(messageHeaders)
    }

}