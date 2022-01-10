package thkoeln.dungeon.botlin.eventConsumer.core

import org.springframework.messaging.MessageHeaders
import java.util.*
import javax.persistence.Id
import javax.persistence.MappedSuperclass


@MappedSuperclass
abstract class AbstractEvent() {
    @Id
    protected var id = UUID.randomUUID();
    protected var eventId: UUID? = null;
    protected var timestamp: Long? = null;
    protected var transactionId: UUID? = null;

    private companion object
    {
        val TRANSACTION_ID_KEY: String = "transactionId";
    }
    constructor(messageHeaders: MessageHeaders) : this() {
        eventId = messageHeaders.id;
        timestamp = messageHeaders.timestamp;
        transactionId = UUID.fromString(messageHeaders[TRANSACTION_ID_KEY].toString())

    }


}