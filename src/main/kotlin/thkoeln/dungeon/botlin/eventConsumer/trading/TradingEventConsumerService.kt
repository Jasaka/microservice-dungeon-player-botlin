package thkoeln.dungeon.botlin.eventConsumer.trading

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
class TradingEventConsumerService {

    @KafkaListener(topics = ["bank-created"])
    fun consumeBankCreatedEvent(@Header eventId: String, @Header timestamp: String, @Header transactionId: String,
                                @Payload payload: String) {
        val bankCreatedEvent = TradingEvent(eventId, timestamp, transactionId, payload)
        if (bankCreatedEvent.isValid()) {
            TODO()
        }
    }
}