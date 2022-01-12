package thkoeln.dungeon.botlin.eventConsumer;

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import kafka.utils.Json
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.internals.RecordHeader
import org.assertj.core.api.Assertions.assertThat
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.KafkaListenerEndpointRegistry
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.ContainerTestUtils
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonSerializer
import thkoeln.dungeon.botlin.eventConsumer.game.GameEventConsumerService
import thkoeln.dungeon.botlin.eventConsumer.game.GameStatusEvent


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [GameEventConsumerService::class])
@DirtiesContext
@EnableKafka
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
@EmbeddedKafka(topics = ["status"], partitions = 1, brokerProperties = ["listeners=PLAINTEXT://localhost:9092", "port=9092"])
class GameEventConsumerSimpleKafkaTest {

    @Autowired
    lateinit var gameStatusEventConsumerService: GameEventConsumerService

    @Autowired //IntelliJ wrongfully says this is not found
    lateinit var kafkaEmbedded: EmbeddedKafkaBroker

    @Autowired
    lateinit var kafkaListenerEndpointRegistry: KafkaListenerEndpointRegistry;

    private lateinit var producer: KafkaTemplate<String, String>

    fun buildProducer() {
        this.producer = buildKafkaTemplate()
    }


    private fun buildKafkaTemplate(): KafkaTemplate<String, String> {
        var senderProps = KafkaTestUtils.producerProps(kafkaEmbedded)
        var pf: ProducerFactory<String, String> = DefaultKafkaProducerFactory(senderProps)
        return KafkaTemplate(pf)
    }


    @Before
    fun setUp() {
        buildProducer()
        for (messageListenerContainer in kafkaListenerEndpointRegistry.listenerContainers) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer, kafkaEmbedded.partitionsPerTopic)
        }
    }

    fun buildRecordHeader(): ProducerRecord<String, String> {
        var headerString = "{\"eventId\":\"5bc9f935-32f1-4d7b-a90c-ff0e6e34125a\"," +
                "\"transactionId\":\"0cfc04f1-6df5-42c6-a19a-146128b8a3b4\"," +
                "\"version\":42," +
                "\"timestamp\":{}," +
                "\"type\":\"event-example-uploaded\"}"

        var payloadString =  "{\"gameId\":\"5bc9f935-32f1-4d7b-a90c-ff0e6e34125a\",\"status\":\"created\"}"
        var mapper: ObjectMapper = ObjectMapper()
        var producerRecord: ProducerRecord<String, String> = ProducerRecord<String, String>("status", payloadString)
        var jsontree: JsonNode = mapper.readTree(headerString)
        for (header in jsontree.fields()) {
            producerRecord.headers().add(RecordHeader(header.key.toString(), header.value.toString().toByteArray()))
        }
        return producerRecord
    }

    @Test
    fun testConsume_receiveMessage() {
        var message = buildRecordHeader();
        producer.send(message)
        println("Game Event List ${GameEventConsumerService.gameEventList}")
        assertThat(GameEventConsumerService.gameEventList.size != 0).isTrue
    }
}
