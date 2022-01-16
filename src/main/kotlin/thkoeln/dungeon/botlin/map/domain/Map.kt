package thkoeln.dungeon.botlin.map.domain

import thkoeln.dungeon.botlin.core.Gravity
import thkoeln.dungeon.botlin.core.Resource
import thkoeln.dungeon.botlin.map.domain.planet.Planet
import java.util.*

class Map {
    private val planets = mutableMapOf<UUID, Planet>()

    fun addPlanet(
        planetId: UUID,
        isSpaceStation: Boolean,
        northNeighbour: UUID,
        southNeighbour: UUID,
        eastNeighbour: UUID,
        westNeighbour: UUID,
        gravity: Gravity,
        resourceType: Resource,
        resourceAmount: Int
    ) {
        planets[planetId] =
            Planet(
                isSpaceStation,
                northNeighbour,
                southNeighbour,
                eastNeighbour,
                westNeighbour,
                gravity,
                Pair(first = resourceType, second = resourceAmount)
            )
    }

    fun findNearestSpaceStation() {

    }

    fun findNearestPlanetWithResource(resourceType: Resource) {

    }

    fun generateRouteToPlanet(planetId: UUID) {

    }
}