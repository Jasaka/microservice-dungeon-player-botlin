package thkoeln.dungeon.botlin.eventConsumer.game

import thkoeln.dungeon.botlin.game.domain.GameStatus
import java.util.*

data class GameStatusEventPayload(var gameId : UUID, var gameStatus : GameStatus) {

}
