package thkoeln.dungeon.botlin.eventConsumer.game

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import thkoeln.dungeon.botlin.game.application.GameApplicationService
import thkoeln.dungeon.botlin.game.domain.GameStatus

@Service
class GameEventConsumerService {
    var gameApplicationService: GameApplicationService?

    //Todo var playerApplicationService : PlayerApplicationService
    constructor(gameApplicationService: GameApplicationService?) {
        this.gameApplicationService = gameApplicationService
    }

    @KafkaListener(topics = ["status"])
    fun consumeGameStatusEvent(@Header eventId: String, @Header timestamp: String, @Header transactionId: String,
                               @Payload payload: String
    ) {
        val gameStatusEvent = GameStatusEvent(eventId, timestamp, transactionId, payload);
        gameEventList.add(gameStatusEvent)
        if (gameStatusEvent.isValid()) {
            when (gameStatusEvent.getGameStatus()) {
                //GameStatus.CREATED ->
                GameStatus.GAME_RUNNING -> gameApplicationService?.gameExternallyStarted(gameStatusEvent.getGameId()!!)
                //GameStatus.GAME_FINISHED -> gameApplicationService.gameExternallyFinished(gameStatusEvent.getGameId())
            }
        }
    }

    companion object {
        val gameEventList: MutableList<GameStatusEvent> = mutableListOf();
    }

    @KafkaListener(topics = ["roundStarted"])
    fun consumeNewRoundStartedEvent() {
//todo
    }

}