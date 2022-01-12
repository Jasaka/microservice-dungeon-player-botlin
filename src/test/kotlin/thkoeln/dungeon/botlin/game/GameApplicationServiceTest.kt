package thkoeln.dungeon.botlin.game

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import thkoeln.dungeon.botlin.game.application.GameApplicationService
import thkoeln.dungeon.botlin.game.domain.Game
import thkoeln.dungeon.botlin.game.domain.GameRepository
import thkoeln.dungeon.botlin.game.domain.GameStatus
import java.util.*
import kotlin.jvm.Throws

@RunWith(SpringRunner::class)
@SpringBootTest
@EnableAutoConfiguration
class GameApplicationServiceTest {
    //Only ID because we will only have one game!
    private var GAME_ID = UUID.randomUUID()
    private lateinit var game0: Game

    @Autowired
    private lateinit var gameRepository: GameRepository

    @Autowired
    private lateinit var gameApplicationService: GameApplicationService

    @Before
    @Throws(Exception::class)
    fun setUp() {
        gameRepository.deleteAll()
        game0 = Game.newlyCreatedGame(GAME_ID)
    }

    fun testHelper(game: Game ,checkedStatus : GameStatus){
        assertTrue(gameRepository.existsByGameID(GAME_ID))
        assertEquals(1, gameRepository.findAllByGameStatusEquals(checkedStatus).size)
        assertEquals(game, gameRepository.findAllByGameStatusEquals(checkedStatus)[0])
    }

    @Test
    fun testGameExternallyCreated() {
        var game = gameApplicationService.gameExternallyCreated(GAME_ID)
        assertTrue(gameApplicationService.retrieveActiveGames().isEmpty())
        testHelper(game,GameStatus.CREATED)
    }
    @Test
    fun testGameExternallyStarted(){

        var game = gameApplicationService.gameExternallyStarted(GAME_ID)
        assertFalse(gameApplicationService.retrieveActiveGames().isEmpty())
        testHelper(game,GameStatus.RUNNING)
    }
    @Test
    fun testGameExternallyFinished(){

        var game = gameApplicationService.gameExternallyFinished(GAME_ID)
        testHelper(game,GameStatus.FINISHED)
    }



}