package thkoeln.dungeon.botlin.strategy

import thkoeln.dungeon.botlin.robot.Robot
import java.util.*


interface Strategy {
    var robot : Robot
    fun executeOn(robot: Robot){
        this.robot = robot
    }
    fun onSpawn(planet: Planet,neighbourPlanets: List<Planet>)
    fun onPlanet(planet: Planet,neighbourPlanets: List<Planet>)
}

class Planet { //TODO MOCK
    lateinit var id : UUID
    var isSpaceStation : Boolean = false
    lateinit var northernNeighbour : UUID
    lateinit var easternNeighbour : UUID
    lateinit var southernNeighbour : UUID
    lateinit var westernNeighbour : UUID
    var gravity = 0
    var resources :Pair<String,Int> = Pair("Coal",30)

}
