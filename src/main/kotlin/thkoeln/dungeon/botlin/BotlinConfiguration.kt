package thkoeln.dungeon.botlin

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter

@Configuration
@EnableAutoConfiguration
class BotlinConfiguration {

    @Autowired
    lateinit var env: Environment

    @Bean
    fun byteArrayJsonMessageConverter(): ByteArrayJsonMessageConverter = ByteArrayJsonMessageConverter()

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        var props = hashMapOf<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = env.getProperty("spring.kafka.bootstrap-servers")!!
        props[ConsumerConfig.GROUP_ID_CONFIG] = env.getProperty("spring.kafka.consumer.group-id")!!
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = env.getProperty("spring.kafka.consumer.auto-offset-reset")!!
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = ByteArrayDeserializer::class.java
        return DefaultKafkaConsumerFactory(props)
    }
    @Bean
    fun kafkaListenerContainerFactory() : ConcurrentKafkaListenerContainerFactory<String,Any>
    {

        var factory = ConcurrentKafkaListenerContainerFactory<String,Any>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

}