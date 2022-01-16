package thkoeln.dungeon.botlin.map.domain.planet

import thkoeln.dungeon.botlin.api.exceptions.BotlinException

class PlanetException(message: String = "Planet does not exist."): BotlinException(message) {
}