package thkoeln.dungeon.botlin.eventConsumer.game

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class GameEventConsumerService {
    //TODO: add GameApplicationService!
    private var gameStatusEventRepository: GameStatusEventRepository? = null

    @Autowired
    constructor(gameStatusEventRepository: GameStatusEventRepository?) {
        this.gameStatusEventRepository = gameStatusEventRepository
    }


     @KafkaListener(topics = ["status"])
     fun consumeGameStatusEvent(
             @Payload gameStatusEventPayload: GameStatusEventPayload,
             headers : MessageHeaders
     )
     {
         val gameStatusEvent = GameStatusEvent(headers,gameStatusEventPayload);
         gameStatusEventRepository?.save(gameStatusEvent);
     }
    
    fun getGameStatusEventRepoSize() : Long? = gameStatusEventRepository?.count();

}