package thkoeln.dungeon.botlin.rest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ClientController {

    @DeleteMapping
    fun forbidDelete(): ResponseEntity<*> = ResponseEntity<Any>(HttpStatus.FORBIDDEN)

    @GetMapping("/")
    fun getSome(): String = "API Information"

    @GetMapping("/games")
    fun getGames(): String = "All Games"

    @GetMapping("/games/{game-id}")
    fun getGame(@PathVariable("game-id") gameId: UUID): String = "Game $gameId"

    @GetMapping("/games/{game-id}/robots")
    fun getRobots(@PathVariable("game-id") gameId: UUID): String = "All Robots in Game $gameId"

    @GetMapping("/games/{game-id}/robots/{robot-id}")
    fun getRobot(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String = "Robot $robotId in Game $gameId"

    @GetMapping("/games/{game-id}/robots/{robot-id}/energy")
    fun getRobotEnergy(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String = "Energy of Robot $robotId in Game $gameId"

    @GetMapping("/games/{game-id}/robots/{robot-id}/items")
    fun getRobotItems(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String = "Items of Robot $robotId in Game $gameId"

    @GetMapping("/games/{game-id}/robots/{robot-id}/ressources")
    fun getRobotRessources(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String = "Ressources of Robot $robotId in Game $gameId"

    @GetMapping("/games/{game-id}/map/planets")
    fun getKnownPlanets(@PathVariable("game-id") gameId: UUID): String = "Planets in Game $gameId"

    @GetMapping("/games/{game-id}/map/planets/{planet-id}")
    fun getPlanet(@PathVariable("game-id") gameId: UUID, @PathVariable("planet-id") planetId: UUID): String = "Planet $planetId in Game $gameId"

    @GetMapping("/games/{game-id}/map")
    fun getMap(@PathVariable("game-id") gameId: UUID): String = "Map of Game $gameId"

    @GetMapping("/games/{game-id}/player")
    fun getPlayer(@PathVariable("game-id") gameId: UUID): String = "Player status in Game $gameId"

    @PostMapping("/games/{game-id}/robots/{robot-id}/command")
    fun issueCommand(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String = "Command to $robotId"

    @PutMapping("/games/{game-id}/robots/{robot-id}/mode")
    fun changeRobotMode(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String = "Robot $robotId Mode"

    @PutMapping("/games/{game-id}/robots/mode")
    fun changeSwarmMode(@PathVariable("game-id") gameId: UUID): String = "Swarm Mode"


}