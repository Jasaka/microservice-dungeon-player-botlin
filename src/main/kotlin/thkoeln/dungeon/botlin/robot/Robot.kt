package thkoeln.dungeon.botlin.robot

import thkoeln.dungeon.botlin.strategy.Planet
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

enum class Resource
{
    COAL,
    CHEESE,
    NONE
}
enum class Item
{
    REFRESH,
    ROBOT,
    UPGRADE,
    NONE
}

@Entity
class Robot() {
    @OneToMany
    val visitedPlanets: MutableList<Planet> = mutableListOf()
    val resources: Pair<Resource,Int> = Pair(Resource.NONE,0);
    @Transient
    val items: MutableMap<Item,Int> = mutableMapOf();

    @Id
    var id = UUID.randomUUID()

    //TODO
    var energy = 0
    var level = 1
    var health = 1
    lateinit var currentPlanet: UUID

    //var strategy : Strategy
    fun executeStrategy() {
        //strategy.run(this)
    }

    fun regenerate() {
        TODO("Not yet implemented")
    }

    fun move(planet: Planet) {
        TODO("Not yet implemented")
    }

    fun mine() {
        TODO("Not yet implemented")
    }

    fun sell() {
        TODO("Not yet implemented")
    }

    fun buy(refresh: Any) {

    }

    constructor(id: UUID, energy: Int, level: Int, health: Int,
                currentPlanet: UUID) : this() {
        this.id = id
        this.energy = energy
        this.level = level
        this.health = health
        this.currentPlanet = currentPlanet
    }
    constructor(robotPayloadDto: RobotPayloadDto) : this() {
        this.id = robotPayloadDto.id
        this.energy = robotPayloadDto.energy
        this.level = robotPayloadDto.level
        this.health = robotPayloadDto.health
        this.currentPlanet = robotPayloadDto.currentPlanet
    }

}