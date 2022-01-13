package thkoeln.dungeon.botlin.robot

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Robot() {
    @Id
    var id = UUID.randomUUID()

    //TODO
    //var resources : MutableMap<Resource,Int> = mutableMapOf()
    //var items : MutableMap<Item,Int> = mutableMapOf()
    var energy = 0
    var level = 1
    var health = 1
    lateinit var currentPlanet: UUID

    //var strategy : Strategy
    fun executeStrategy() {
        //strategy.run(this)
    }

    constructor(id: UUID, energy: Int, level: Int, health: Int,
                currentPlanet: UUID) : this() {
        this.id = id
        this.energy = energy
        this.level = level
        this.currentPlanet = currentPlanet
    }

}