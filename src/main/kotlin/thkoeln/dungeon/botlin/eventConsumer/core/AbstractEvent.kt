package thkoeln.dungeon.botlin.eventConsumer.core

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Transient


@MappedSuperclass
abstract class AbstractEvent(){


    @Id
    protected var id = UUID.randomUUID();
    protected var eventId: UUID? = null
    protected var timestamp: Long? = null
    protected var transactionId: UUID? = null

    constructor(eventIdString: String, timestampString: String, transactionIdString: String) : this() {
        eventId = UUID.fromString(eventIdString)
        timestamp = timestampString.toLong();
        transactionId = UUID.fromString(transactionIdString);

    }

    private companion object {
        val TRANSACTION_ID_KEY: String = "transactionId";
    }


}