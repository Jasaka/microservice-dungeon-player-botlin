package thkoeln.dungeon.botlin.map.domain

import thkoeln.dungeon.botlin.core.Gravity
import thkoeln.dungeon.botlin.core.Resource
import thkoeln.dungeon.botlin.map.domain.planet.Planet
import thkoeln.dungeon.botlin.map.domain.planet.PlanetException
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

    fun generateRouteTo(goal: SearchType, goalPlanetId: UUID?) {
        if (goal == SearchType.PLANET && goalPlanetId == null){
            throw PlanetException("Searching for Planet without providing planetId.")
        }
        if (goal == SearchType.PLANET && planets[goalPlanetId] == null){
            throw PlanetException("Searched Planet is not currently known.")
        }
        // TODO: CheckAlgorithm
    }

/* TODO CheckAlgorithm
    up:
    checkBounds(left){
        checkUpLeft
        up
    }
    checkBounds(up){
        checkUpUp
        up
    }
    down:
    checkBounds(down){
        checkDownDown
        down
    }
    checkBounds(right){
        checkDownRight
        down
    }
    left:
    checkBounds(left){
        checkLeftLeft
        left
    }
    checkBounds(down){
        checkLeftDown
        left
    }
    right:
    checkBounds(right){
        checkRightRight
        right
    }
    checkBounds(up){
        checkRightUp
        right
    }
    ---
    checkXX{
        if(traversed)
            break
        else
            continue
    }

    checkBounds(direction){
        if(direction == outOfBounds)
            break
        else
            continue
    }*/

    private fun checkForSuccessCondition(goal: SearchType, searchedPlanetId: UUID, currentPlanetId: UUID): Boolean?{
        val currentPlanet = planets[currentPlanetId] ?: return null
        val searchedPlanet = planets[searchedPlanetId] ?: return null
        return when(goal){
            SearchType.STATION -> {
                currentPlanet.isSpaceStation
            }
            SearchType.PLANET -> {
                searchedPlanetId == currentPlanetId
            }
            SearchType.RESOURCE -> {
                currentPlanet.getResource() == searchedPlanet.getResource()
            }
        }
    }
}