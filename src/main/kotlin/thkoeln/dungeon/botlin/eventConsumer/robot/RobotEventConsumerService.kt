package thkoeln.dungeon.botlin.eventConsumer.robot

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class RobotEventConsumerService {
    @KafkaListener(topics = ["movement"])
    fun consumeMovementEvent(@Header eventId: String, @Header timestamp: String, @Header transactionId: String,
                             @Payload payload: String) {
        val movementEvent = MovementEvent(eventId, timestamp, transactionId, payload)
        if (movementEvent.isValid()) {
            TODO()
        }
    }

    @KafkaListener(topics = ["resource-mined"])
    fun consumeResourceMinedEvent(@Header eventId: String, @Header timestamp: String, @Header transactionId: String,
                                  @Payload payload: String) {
        val finishedMiningEvent = FinishedMiningEvent(eventId, timestamp, transactionId, payload)
        TODO()
    }

    @KafkaListener(topics = ["mining"])
    fun consumeMiningEvent(@Header eventId: String, @Header timestamp: String, @Header transactionId: String,
                           @Payload payload: String) {
        val miningEvent = MiningEvent(eventId, timestamp, transactionId, payload)
        TODO()
    }

    @KafkaListener(topics = ["spawn-notification"])
    fun consumeSpawnNotificationEvent(@Header eventId: String, @Header timestamp: String, @Header transactionId: String,
                                      @Payload payload: String) {
        val spawnNotificationEvent = SpawnNotificationEvent(eventId, timestamp, transactionId, payload)
    }
}