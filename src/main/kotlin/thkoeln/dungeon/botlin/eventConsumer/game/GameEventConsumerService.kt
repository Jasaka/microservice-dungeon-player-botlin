package thkoeln.dungeon.botlin.eventConsumer.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import thkoeln.dungeon.botlin.eventConsumer.player.PlayerStatus
import thkoeln.dungeon.botlin.eventConsumer.player.PlayerStatusEvent
import thkoeln.dungeon.botlin.game.application.GameApplicationService
import thkoeln.dungeon.botlin.game.domain.GameStatus
import thkoeln.dungeon.botlin.game.domain.RoundStatus

@Service
class GameEventConsumerService {
    var gameApplicationService: GameApplicationService?

    companion object {
        val gameEventList: MutableList<GameStatusEvent> = mutableListOf();
    }

    //Todo var playerApplicationService : PlayerApplicationService
    constructor(gameApplicationService: GameApplicationService?) {
        this.gameApplicationService = gameApplicationService
    }

    @KafkaListener(topics = ["status"])
    fun consumeGameStatusEvent(@Header eventId: String, @Header timestamp: String, @Header transactionId: String,
                               @Payload payload: String
    ) {
        println("Game Status Header: ")
        println(eventId)
        println(timestamp)
        println(transactionId)
        println("Game Status payload: $payload")

        val gameStatusEvent = GameStatusEvent(eventId, timestamp, transactionId, payload);
        gameEventList.add(gameStatusEvent)
        if (gameStatusEvent.isValid()) {
            when (gameStatusEvent.getGameStatus()) {
                //GameStatus.CREATED ->
                GameStatus.RUNNING -> gameApplicationService?.gameExternallyStarted(gameStatusEvent.getGameId()!!)
                GameStatus.FINISHED -> gameApplicationService?.gameExternallyFinished(gameStatusEvent.getGameId()!!)
                //TODO doesnt join the player
                GameStatus.CREATED -> gameApplicationService?.gameExternallyCreated(gameStatusEvent.getGameId()!!)
                GameStatus.ORPHANED -> TODO()
                null -> TODO()
            }
        }
    }


    @KafkaListener(topics = ["playerStatus"])
    fun consumePlayerStatusEvent(@Header eventId: String, @Header timestamp: String, @Header transactionId: String,
                                    @Payload payload: String) {
        val playerStatusEvent = PlayerStatusEvent(eventId, timestamp, transactionId, payload)
        if (playerStatusEvent.isValid()) {
            when (playerStatusEvent.getPlayerStatus()) {
                PlayerStatus.JOINED -> TODO()
            }
        }
    }

    @KafkaListener(topics = ["roundStatus"])
    fun consumeRoundStatusEvent(@Header eventId: String, @Header timestamp: String, @Header transactionId: String,
                                @Payload payload: String)
    {
        val roundStatusEvent = RoundStatusEvent(eventId,timestamp,transactionId,payload)
        if(roundStatusEvent.isValid())
        {
            when(roundStatusEvent.getRoundStatus())
            {
                RoundStatus.STARTED -> gameApplicationService?.newRound(roundStatusEvent.getRoundNumber())
                RoundStatus.COMMAND_END -> TODO() //Rest send command for every robots
                RoundStatus.ROUND_END -> TODO()
            }
        }

    }



}