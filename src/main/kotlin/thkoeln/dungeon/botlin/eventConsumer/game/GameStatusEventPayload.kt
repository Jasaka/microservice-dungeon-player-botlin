package thkoeln.dungeon.botlin.eventConsumer.game

import org.apache.kafka.common.protocol.types.Field
import thkoeln.dungeon.botlin.game.domain.GameStatus

data class GameStatusEventPayload(var gameId : Field.UUID, var gameStatus : GameStatus) {

}
