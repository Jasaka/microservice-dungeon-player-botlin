package thkoeln.dungeon.botlin

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan("thkoeln.dungeon.botlin.*")
@EnableJpaRepositories("thkoeln.dungeon.botlin.eventConsumer.game")
@SpringBootApplication(exclude=[DataSourceAutoConfiguration::class])
open class BotlinApplication

fun main(args: Array<String>) {
	runApplication<BotlinApplication>(*args)
}
