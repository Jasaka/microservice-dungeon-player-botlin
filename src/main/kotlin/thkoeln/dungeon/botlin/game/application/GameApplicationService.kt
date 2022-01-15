package thkoeln.dungeon.botlin.game.application

import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import thkoeln.dungeon.botlin.game.domain.Game
import thkoeln.dungeon.botlin.game.domain.GameRepository
import thkoeln.dungeon.botlin.game.domain.GameStatus
import thkoeln.dungeon.botlin.player.PlayerRepository
import thkoeln.dungeon.botlin.user.User
import java.util.*

@Service
class GameApplicationService {
    private var gameRepository: GameRepository?
    private var playerRepository: PlayerRepository
    //TODO: GameServiceRESTAdapter

    private var logger: Logger = LoggerFactory.getLogger(GameApplicationService::class.java)
    var modelMapper: ModelMapper = ModelMapper();

    @Autowired
    constructor(gameRepository: GameRepository?,playerRepository: PlayerRepository
    ) {
        this.gameRepository = gameRepository
        this.playerRepository = playerRepository
    }

    fun retrieveActiveGames(): List<Game> {
        return gameRepository!!.findAllByGameStatusEquals(GameStatus.RUNNING)
    }

    fun gameStatusExternallyChanged(gameId: UUID, gameStatus: GameStatus) {
        when (gameStatus) {
            GameStatus.CREATED -> gameExternallyCreated(gameId)
        }
    }

    fun gameExternallyCreated(gameId: UUID): Game {
        logger.info("Processing external event that the game has been created")
        var game = findAndIfNeededCreateGame(gameId)
        game.resetToNewlyCreated()
        gameRepository?.save(game)
        return game;
    }

    fun findAndIfNeededCreateGame(gameId: UUID) : Game
    {
        var fittingGames = gameRepository!!.findByGameID(gameId)
        var game: Game? = null
        if (fittingGames.isEmpty()) {
            game = Game.newlyCreatedGame(gameId)
        } else {
            if (fittingGames.size > 1) {
                game = fittingGames[0]
            }
        }
        gameRepository!!.save(game!!)
        return game
    }

    fun gameExternallyStarted(gameId: UUID): Game {
        logger.info("Processing external event that the game with id $gameId has started")
        var allGames: List<Game>? = gameRepository?.findAll()
        if (allGames != null) {
            for (game in allGames) {
                game.gameStatus = GameStatus.FINISHED
                gameRepository?.save(game)
            }
        }
        var game = findAndIfNeededCreateGame(gameId)
        game.gameStatus = GameStatus.RUNNING
        gameRepository?.save(game)
        return game
    }

    fun gameExternallyFinished(gameId: UUID): Game {
        logger.info("Processing external event that the game with gameId $gameId has ended")
        var game = findAndIfNeededCreateGame(gameId)
        game.gameStatus = GameStatus.FINISHED
        gameRepository?.save(game)
        return game
    }
    fun newRound(roundNumber: Int)
    {
        logger.info("Processing 'new round' event for round no. $roundNumber")
        var game = findAndIfNeededCreateGame(gameRepository?.findFirstByGameStatusEquals(GameStatus.RUNNING)!!.gameID)
        game.currentRoundCount = roundNumber
        gameRepository?.save(game)
        //todo
    }

    fun newUserJoinedGame(user: User, game: Game, transactionId: UUID) {
        var player = user.participate(transactionId)
        game.players.add(player)
        playerRepository.save(player)
        gameRepository?.save(game)
    }
    //todo synchronize game state ?
}