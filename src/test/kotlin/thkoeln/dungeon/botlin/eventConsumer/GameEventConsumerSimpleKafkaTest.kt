package thkoeln.dungeon.botlin.eventConsumer;

import org.assertj.core.api.Assertions.assertThat
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
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import thkoeln.dungeon.botlin.eventConsumer.game.GameEventConsumerService


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

    @Before
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
        for (messageListenerContainer in kafkaListenerEndpointRegistry.listenerContainers) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer, kafkaEmbedded.partitionsPerTopic)
        }
    }

    @Test
    fun testConsume_receiveMessage() {
        //var kafkaTemplate = TestConfig().kafkaTemplate()
        producer.send("status", "{\n" +
                "  \"gameId\": \"5bc9f935-32f1-4d7b-a90c-ff0e6e34125a\",\n" +
                "  \"status\": \"created\"\n" +
                "}")
        println("Game Event List ${GameEventConsumerService.gameEventList}")
        assertThat(GameEventConsumerService.gameEventList.size != 0).isTrue
    }
}
