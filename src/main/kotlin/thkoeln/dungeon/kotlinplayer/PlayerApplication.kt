package thkoeln.dungeon.kotlinplayer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlayerApplication

fun main(args: Array<String>) {
	runApplication<PlayerApplication>(*args)
}
