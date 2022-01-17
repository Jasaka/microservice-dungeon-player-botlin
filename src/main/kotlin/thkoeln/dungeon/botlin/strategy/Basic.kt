package thkoeln.dungeon.botlin.strategy

import thkoeln.dungeon.botlin.robot.Robot
import java.util.*

abstract class Basic : Strategy {
    var random = Random()
    lateinit var currentPlanet: Planet
    override fun executeOn(robot: Robot) {
        this.robot = robot
    }

    override fun onSpawn(planet: Planet,neighbourPlanets: List<Planet>) {
        currentPlanet = planet
        checkNeighbours(neighbourPlanets)

    }

    override fun onPlanet(planet: Planet,neighbourPlanets: List<Planet>) {
        currentPlanet = planet
        checkNeighbours(neighbourPlanets)
        if(robot.resources.second <= 10) //TODO hardcoded inventory size
        {

        }
        if (planet.resources.first != "NONE") {
            robot.mine()
        }
    }

    fun checkNeighbours(neighbourPlanets: List<Planet>) {
        for (planet in neighbourPlanets) {
            if (planet.resources.first != "NONE") {
                robot.move(planet.id)
            }
        }
        var direction = random.nextInt(3)
        when (direction) {
            0 -> robot.move(currentPlanet.northernNeighbour)
            1 -> robot.move(currentPlanet.easternNeighbour)
            2 -> robot.move(currentPlanet.southernNeighbour)
            3 -> robot.move(currentPlanet.westernNeighbour)
        }
    }
}


