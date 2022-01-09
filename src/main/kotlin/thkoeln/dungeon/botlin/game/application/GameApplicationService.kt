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
    private lateinit var gameRepository: GameRepository
    //TODO: GameServiceRESTAdapter

    private var logger: Logger = LoggerFactory.getLogger(GameApplicationService::class.java)
    var modelMapper: ModelMapper = ModelMapper();

    @Autowired
    constructor(gameRepository: GameRepository
    ) {
        this.gameRepository = gameRepository
    }

    fun retrieveActiveGames(): List<Game> {
        return gameRepository.findAllByGameStatusEquals(GameStatus.GAME_RUNNING)
    }

    fun gameStatusExternallyChanged(gameId: UUID, gameStatus: GameStatus) {
        when (gameStatus) {
            GameStatus.CREATED -> gameExternallyCreated(gameId)
        }
    }

    fun gameExternallyCreated(gameId: UUID) {
        logger.info("Processing external event that the game has been created")
        var fittingGames = gameRepository.findByGameId(gameId)
        var game: Game? = null
        if (fittingGames.isEmpty()) {
            game = Game.newlyCreatedGame(gameId)
            gameRepository.save(game)
        } else {
            if (fittingGames.size > 1)
                game = mergeGamesIntoOne(fittingGames)
            game!!.resetToNewlyCreated();
            gameRepository.save(game)
        }

    }

    fun mergeGamesIntoOne(fittingGames: List<Game>): Game {
        //Implement
        return fittingGames[0];
    }

    fun gameExternallyStarted(eventId: UUID) {
        logger.info("Processing external event that the game with id $eventId has started")
        val foundGames = gameRepository.findAllByGameStatusEquals(GameStatus.GAME_RUNNING)
    }


}