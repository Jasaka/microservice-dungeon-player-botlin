package thkoeln.dungeon.botlin.eventConsumer.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import thkoeln.dungeon.botlin.game.application.GameApplicationService

@Service
class GameEventConsumerService  {
    var gameStatusEventRepository: GameStatusEventRepository
    var gameApplicationService: GameApplicationService
    @Autowired constructor(gameStatusEventRepository: GameStatusEventRepository,
                           gameApplicationService: GameApplicationService)
    {
        this.gameApplicationService = gameApplicationService;
        this.gameStatusEventRepository = gameStatusEventRepository
    }

    @KafkaListener(topics = ["status"])
    fun consumeGameStatusEvent(
            @Payload gameStatusEventPayload: GameStatusEventPayload,
            headers: MessageHeaders
    ) {
        val gameStatusEvent = GameStatusEvent(headers, gameStatusEventPayload);
        gameStatusEventRepository.save(gameStatusEvent);
        gameApplicationService.gameStatusExternallyChanged(gameStatusEvent.getGameId()!!, gameStatusEvent.getGameStatus()!!)
    }

    fun getGameStatusEventRepoSize(): Long = gameStatusEventRepository.count();

}