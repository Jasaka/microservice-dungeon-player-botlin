package thkoeln.dungeon.botlin.eventConsumer.mock

import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.stereotype.Component
import java.util.concurrent.Future

class MockGameEventProducer(private val producer: Producer<String, String>) {

    fun send(key : String,value : String): Future<RecordMetadata>
    {
        val record : ProducerRecord<String,String> = ProducerRecord("status",key,value);
        return producer.send(record);
    }

}