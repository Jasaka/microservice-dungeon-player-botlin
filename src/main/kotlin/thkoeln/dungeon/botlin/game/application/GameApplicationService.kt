package thkoeln.dungeon.botlin.game.application

import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import thkoeln.dungeon.botlin.game.domain.Game
import thkoeln.dungeon.botlin.game.domain.GameRepository
import thkoeln.dungeon.botlin.game.domain.GameStatus
import java.util.*

@Service
class GameApplicationService {
    private var gameRepository: GameRepository?
    //TODO: GameServiceRESTAdapter

    private var logger: Logger = LoggerFactory.getLogger(GameApplicationService::class.java)
    var modelMapper: ModelMapper = ModelMapper();

    @Autowired
    constructor(gameRepository: GameRepository?
    ) {
        this.gameRepository = gameRepository
    }

    fun retrieveActiveGames(): List<Game> {
        return gameRepository!!.findAllByGameStatusEquals(GameStatus.RUNNING)
    }


    fun gameStatusExternallyChanged(gameId: UUID, gameStatus: GameStatus) {
        when (gameStatus) {
            GameStatus.CREATED -> gameExternallyCreated(gameId)
        }
    }

    fun gameExternallyCreated(gameId: UUID) : Game{
        logger.info("Processing external event that the game has been created")
        var game = findAndIfNeededCreateGame(gameId)
        game.resetToNewlyCreated()
        gameRepository?.save(game)
        return game;
    }
    fun findAndIfNeededCreateGame(gameId: UUID) : Game
    {
        var fittingGames = gameRepository!!.findByGameId(gameId)
        var game: Game? = null
        if (fittingGames.isEmpty()) {
            game = Game.newlyCreatedGame(gameId)
            gameRepository!!.save(game)
        } else {
            if (fittingGames.size > 1)
            {
            game = fittingGames[0]
            }
            game!!.resetToNewlyCreated();
        }
        gameRepository!!.save(game)
return game
    }

    fun gameExternallyStarted(gameId: UUID) : Game {
        logger.info("Processing external event that the game with id $gameId has started")
        var allGames : List<Game>? = gameRepository?.findAll()
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

    fun gameExternallyFinished(gameId: UUID) : Game{
        logger.info("Processing external event that the game with gameId $gameId has ended")
        var game = findAndIfNeededCreateGame(gameId)
        game.gameStatus = GameStatus.FINISHED
        gameRepository?.save(game)
        return game
    }
    fun newRound(gameId: UUID,roundNumber: Int)
    {
        logger.info("Processing 'new round' event for round no. $roundNumber")
        //todo
    }

    //todo synchronize game state ?



}