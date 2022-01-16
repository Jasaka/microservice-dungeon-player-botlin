package thkoeln.dungeon.botlin.map.domain.planet

import thkoeln.dungeon.botlin.core.Direction
import thkoeln.dungeon.botlin.core.Gravity
import thkoeln.dungeon.botlin.core.Resource
import java.util.*

class Planet(
    private val isSpaceStation: Boolean = false,
    private val northernNeighbour: UUID,
    private val southernNeighbour: UUID,
    private val westernNeighbour: UUID,
    private val easternNeighbour: UUID,
    private var gravity: Gravity,
    private var resources: Pair<Resource, Int>
) {

    fun setResource(resourceType: Resource, amount: Int){
        resources = resources.copy(first = resourceType, second = amount)
    }

    fun updateResourceAmount(amount: Int){
        resources = resources.copy(second = amount)
    }

    fun getRandomNeighbour(): UUID? {
        val random = Random()
        return when(random.nextInt(4)){
            0 -> getNeighbour(Direction.NORTH)
            1 -> getNeighbour(Direction.SOUTH)
            2 -> getNeighbour(Direction.EAST)
            3 -> getNeighbour(Direction.WEST)
            else -> null
        }
    }

    fun getNeighbour(direction: Direction): UUID? {
        return when(direction){
            Direction.NORTH -> northernNeighbour
            Direction.SOUTH -> southernNeighbour
            Direction.EAST -> easternNeighbour
            Direction.WEST -> westernNeighbour
        }
    }

}