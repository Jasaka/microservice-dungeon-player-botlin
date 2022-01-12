package thkoeln.dungeon.botlin.player

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.junit4.SpringRunner
import thkoeln.dungeon.botlin.game.application.GameApplicationService
import thkoeln.dungeon.botlin.game.domain.Game
import thkoeln.dungeon.botlin.game.domain.GameRepository
import java.util.*
import kotlin.jvm.Throws

@RunWith(SpringRunner::class)
@SpringBootTest
@EnableAutoConfiguration
class PlayerApplicationServiceTest {
    //Only ID because we will only have one game!
    private var GAME_ID = UUID.randomUUID()
    private lateinit var game0: Game
    private lateinit var player: Player

    @Autowired
    private lateinit var gameRepository: GameRepository

    @Autowired
    private lateinit var gameApplicationService: GameApplicationService

    @Autowired
    private lateinit var playerRepository: PlayerRepository

    @Autowired
    private lateinit var playerApplicationService: PlayerApplicationService

    @Before
    @Throws(Exception::class)
    fun setUp() {
        gameRepository.deleteAll()
        playerRepository.deleteAll()
        game0 = Game.newlyCreatedGame(GAME_ID)
        player = Player()
        playerRepository.save(player)
    }

    @Test
    fun testRegisterPlayerInOneGame() {
        playerApplicationService.registerOnePlayerForGame(player,game0)
        assertEquals(1,playerRepository.findAll().size)
        assertEquals(1,game0.players.size)
        assertEquals(player,game0.players[0])
    }

}