package thkoeln.dungeon.botlin.eventConsumer;

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.MockProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.Serializer
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import thkoeln.dungeon.botlin.eventConsumer.game.GameEventConsumerService
import thkoeln.dungeon.botlin.eventConsumer.game.GameStatusEventPayload
import thkoeln.dungeon.botlin.eventConsumer.game.GameStatusEventRepository
import thkoeln.dungeon.botlin.eventConsumer.mock.MockGameEventProducer
import thkoeln.dungeon.botlin.game.domain.GameStatus
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(topics = ["status"])
class GameEventConsumerSimpleKafkaTest {
    @Autowired
    lateinit var gameStatusEventConsumerService: GameEventConsumerService

    lateinit var producer: MockProducer<String, String>

    @Before
    fun setUp() {
        producer = MockProducer(true,
                StringSerializer(),
                StringSerializer())
    }


    @Test
    fun testConsume_PersistValidGameStatus_PersistedGameStatusEvent() {
        producer.send(ProducerRecord("status", "GAME_FINISHED"))
        assertTrue(gameStatusEventConsumerService.getGameStatusEventRepoSize()!! > 0)
    }
}
