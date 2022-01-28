package thkoeln.dungeon.botlin.eventConsumer.map

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class MapEventConsumerService {
    @KafkaListener(topics = ["neighbours"])
    fun consumerNeighbourEvent(@Header eventId: String, @Header timestamp: String, @Header transactionId: String,
                               @Payload payload: String){
val neighbourEvent = NeighbourEvent(eventId,timestamp,transactionId,payload)
        if(neighbourEvent.isValid())
        {

        }
    }
}