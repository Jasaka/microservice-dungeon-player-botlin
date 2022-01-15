package thkoeln.dungeon.botlin.player

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import thkoeln.dungeon.botlin.game.application.GameApplicationService
import thkoeln.dungeon.botlin.game.domain.Game
import thkoeln.dungeon.botlin.game.domain.GameRepository
import thkoeln.dungeon.botlin.user.User
import thkoeln.dungeon.botlin.user.UserApplicationService
import thkoeln.dungeon.botlin.user.UserRepository
import java.util.*
import kotlin.jvm.Throws

@RunWith(SpringRunner::class)
@SpringBootTest
@EnableAutoConfiguration
class UserApplicationServiceTest{
    //Only ID because we will only have one game!
    private var GAME_ID = UUID.randomUUID()
    private lateinit var game0: Game
    private lateinit var user:User

    @Autowired
    private lateinit var gameRepository: GameRepository

    @Autowired
    private lateinit var gameApplicationService: GameApplicationService

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var playerRepository:PlayerRepository

    @Autowired
    private lateinit var userApplicationService : UserApplicationService

    @Before
    @Throws(Exception::class)
    fun setUp() {
        gameRepository.deleteAll()
        userRepository.deleteAll()
        playerRepository.deleteAll()
        game0 = Game.newlyCreatedGame(GAME_ID)
        user= User()
        user.bearerToken = UUID.randomUUID()
        userRepository.save(user)
    }

    @Test
    fun testRegisterUserInGame() {
        userApplicationService.registerOneUserForGame(user,game0)
        assertEquals(1,userRepository.findAll().size)
        assertEquals(1,game0.players.size)
    }

}