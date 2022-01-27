package thkoeln.dungeon.botlin.strategy

import thkoeln.dungeon.botlin.robot.Item
import thkoeln.dungeon.botlin.robot.Resource
import thkoeln.dungeon.botlin.robot.Robot
import java.util.*

abstract class Basic : Strategy {
    var random = Random()
    lateinit var currentPlanet: Planet
    override fun executeOn(robot: Robot) {
        this.robot = robot
    }

    override fun onSpawn(planet: Planet, neighbourPlanets: List<Planet>) {
        currentPlanet = planet
        checkNeighbours(neighbourPlanets)

    }

    override fun onPlanet(planet: Planet, neighbourPlanets: List<Planet>) {
        currentPlanet = planet
        checkIfOnStation(planet)
        if (robot.energy <= 1) {
            if (robot.resources.second <= 10) //TODO hardcoded inventory size
            {
                robot.regenerate()
            } else if (robot.resources.second >= 10) {
                var previousPlanet = robot.visitedPlanets.indexOf(planet)-1
                robot.move(robot.visitedPlanets[previousPlanet])
            }

        }
        if (planet.resources.first != "NONE") {
            robot.mine()
        }
        else checkNeighbours(neighbourPlanets)
    }

    private fun checkIfOnStation(planet: Planet) {
        if (planet.isSpaceStation) {
            //ONE OF THESE COMMANDS WILL SUCCEED
            robot.sell()
            robot.buy(Item.REFRESH)
            robot.buy(Item.ROBOT)
            robot.buy(Item.UPGRADE)

            var nextPlanet = robot.visitedPlanets.indexOf(planet)+1
            robot.move(robot.visitedPlanets[nextPlanet])
        }
    }

    private fun checkNeighbours(neighbourPlanets: List<Planet>) {
        for (planet in neighbourPlanets) {
            if (planet.resources.first != "NONE") {
                robot.move(planet)
            }
        }
    }
}


