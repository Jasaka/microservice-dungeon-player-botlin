package thkoeln.dungeon.botlin.player

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import thkoeln.dungeon.botlin.game.application.GameApplicationService
import thkoeln.dungeon.botlin.game.domain.Game
import thkoeln.dungeon.botlin.robot.RobotRepository
import java.util.*

@Service
class PlayerApplicationService {
    private var playerRepository: PlayerRepository;
    private var gameApplicationService: GameApplicationService?
    private var robotRepository : RobotRepository

    @Autowired
    constructor(playerRepository: PlayerRepository, gameApplicationService: GameApplicationService,
    robotRepository: RobotRepository) {
        this.playerRepository = playerRepository
        this.gameApplicationService = gameApplicationService
        this.robotRepository = robotRepository
    }

    fun joinAllInNewGame(gameId: UUID) {
        var game = gameApplicationService!!.gameExternallyCreated(gameId);
        var players = playerRepository.findAll()
        for(player in players) registerOnePlayerForGame(player,game)
    }

    fun registerOnePlayerForGame(player: Player, game: Game) {
        //TODO check user bearer token
        //Todo if userStatus = JOINED create a PLAYER for game !
        gameApplicationService!!.newPlayerJoinedGame(player,game)
        playerRepository.save(player)
    }

    fun assignRobotToPlayer(robotId : UUID,playerId: UUID)
    {
        var robot = robotRepository.findById(robotId).get()
        var player = playerRepository.findById(playerId).get()
        player.robots.add(robot)
        playerRepository.save(player)
    }

}