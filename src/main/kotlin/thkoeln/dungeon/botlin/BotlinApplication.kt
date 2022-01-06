package thkoeln.dungeon.botlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class BotlinApplication

fun main(args: Array<String>) {
	runApplication<BotlinApplication>(*args)
}
