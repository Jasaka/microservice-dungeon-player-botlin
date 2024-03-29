package thkoeln.dungeon.botlin.api
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.*


@RestController
class ClientController {

    val adapter: GameAdapter = GameAdapter()

    @DeleteMapping
    fun forbidDelete(): ResponseEntity<*> = ResponseEntity<Any>(HttpStatus.FORBIDDEN)

    @RequestMapping("/")
    fun frontend(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "index"
        return modelAndView
    }

    @GetMapping("/games")
    fun getGames(): String? = adapter.getAllGames()

    @GetMapping("/games/{game-id}")
    fun getGame(@PathVariable("game-id") gameId: UUID): String? = adapter.getGame(gameId)

    @GetMapping("/games/{game-id}/time")
    fun getGameTime(@PathVariable("game-id") gameId: UUID): String? = adapter.getCurrentTime(gameId)

    @GetMapping("/games/{game-id}/commands")
    fun getCommandsOfRound(@PathVariable("game-id") gameId: UUID): String? = adapter.getCommandsOfRound(gameId)

    // TODO: Mapping
    @GetMapping("/games/{game-id}/robots")
    fun getRobots(@PathVariable("game-id") gameId: UUID): String? = "All Robots in Game $gameId"

    // TODO: Mapping
    @GetMapping("/games/{game-id}/robots/{robot-id}")
    fun getRobot(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String? = "Robot $robotId in Game $gameId"

    // TODO: Mapping
    @GetMapping("/games/{game-id}/robots/{robot-id}/energy")
    fun getRobotEnergy(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String? = "Energy of Robot $robotId in Game $gameId"

    // TODO: Mapping
    @GetMapping("/games/{game-id}/robots/{robot-id}/items")
    fun getRobotItems(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String? = "Items of Robot $robotId in Game $gameId"

    // TODO: Mapping
    @GetMapping("/games/{game-id}/robots/{robot-id}/ressources")
    fun getRobotRessources(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String? = "Ressources of Robot $robotId in Game $gameId"

    // TODO: Mapping
    @GetMapping("/games/{game-id}/map/planets")
    fun getKnownPlanets(@PathVariable("game-id") gameId: UUID): String? = "Planets in Game $gameId"

    // TODO: Mapping
    @GetMapping("/games/{game-id}/map/planets/{planet-id}")
    fun getPlanet(@PathVariable("game-id") gameId: UUID, @PathVariable("planet-id") planetId: UUID): String? = "Planet $planetId in Game $gameId"

    // TODO: Mapping
    @GetMapping("/games/{game-id}/map")
    fun getMap(@PathVariable("game-id") gameId: UUID): String? = "Map of Game $gameId"

    // TODO: Mapping
    @GetMapping("/games/{game-id}/player")
    fun getPlayer(@PathVariable("game-id") gameId: UUID): String? = "Player status in Game $gameId"

    // TODO: Mapping
    @PostMapping("/games/{game-id}/robots/{robot-id}/command")
    fun issueCommand(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String = "Command to $robotId"

    // TODO: Mapping
    @PutMapping("/games/{game-id}/robots/{robot-id}/mode")
    fun changeRobotMode(@PathVariable("game-id") gameId: UUID, @PathVariable("robot-id") robotId: UUID): String = "Robot $robotId Mode"

    // TODO: Mapping
    @PutMapping("/games/{game-id}/robots/mode")
    fun changeSwarmMode(@PathVariable("game-id") gameId: UUID): String = "Swarm Mode"

}