package thkoeln.dungeon.botlin.user

import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import thkoeln.dungeon.botlin.api.GameAdapter
import thkoeln.dungeon.botlin.game.application.GameApplicationService
import thkoeln.dungeon.botlin.game.domain.Game
import thkoeln.dungeon.botlin.player.PlayerRepository
import java.util.*

@Service
class UserApplicationService {

    private val modelMapper = ModelMapper()

    private var playerRepository: PlayerRepository;
    private var gameApplicationService: GameApplicationService?
    private var userRepository: UserRepository
    private var gameAdapter: GameAdapter

    @Value("\${botlin.user.name}")
    private val userName: String = "Mysterious Stranger"

    @Value("\${botlin.user.email}")
    private val userEmail: String = "alles klar"

    @Autowired
    constructor(playerRepository: PlayerRepository, gameApplicationService: GameApplicationService,
                userRepository: UserRepository, gameAdapter: GameAdapter) {
        this.playerRepository = playerRepository
        this.gameApplicationService = gameApplicationService
        this.userRepository = userRepository
        this.gameAdapter = gameAdapter
    }

    fun joinAllInNewGame(gameId: UUID) {
        var game = gameApplicationService!!.gameExternallyCreated(gameId);
        var users = userRepository.findAll()
        for (user in users) registerOneUserForGame(user, game)
    }

    fun registerOneUserForGame(user: User, game: Game) {
        if (user.bearerToken == null) {
            obtainBearerTokenForUser(user)
        }
        //TODO boolean = Rest.registerPlayerForGame
        var transactionDto =
                TransactionIdResponseDto.fromJsonString(
                        gameAdapter.registerPlayer(game.gameID, user.bearerToken)
                )
        var transactionId = transactionDto.transactionId
        if (user.isReadyToPlay()) {
            if (transactionId != null) {
                userRepository.save(user)
                gameApplicationService!!.newUserJoinedGame(user, game, transactionId)
            }
        }
    }


    //TODO needs exceptions!
    fun obtainBearerTokenForUser(user: User) {
        var saveUser = user
        if (saveUser.isReadyToPlay()) {
            var userDto = modelMapper.map(saveUser, UserDto::class)
            var registeredUserDto =
                    UserDto.fromJsonString(
                            gameAdapter.createPlayer(userName, userEmail)
                    )
            if (registeredUserDto != null) {
                saveUser.bearerToken = registeredUserDto.bearerToken
                userRepository.save(saveUser)
            }
        }
    }

    fun obtainBearerTokensForAllUsers() {
        var users: List<User>? = userRepository.findAll()
        if (users != null) {
            for (user in users) obtainBearerTokenForUser(user)
        }
    }

}